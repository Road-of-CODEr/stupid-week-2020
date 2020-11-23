# ARMv8 TLB 

## TLB 정의
Translation Lookaside Buffers(TLBs) 란 주소 변환을 위한 page table walking 과정의 결과를 캐싱하므로써 평균 메모리 접근 비용을 감소시키는 일종의 page table 캐쉬이다.
Virtual Memory System Architecture(VMSA)는 TLB 관리 명령들을 제공한다. 
또한 TLB가 갖는 translation table entry 는 Translation fault, Address size fault, Access flag fault 를 직접적으로 발생시키지 않는다.
즉 위와 같은 exception 이 발생하는 경우 TLB 에 저장하지 않는다는 뜻이다.

## TLB 구성 요소
ARMv8 문서에서 TLB 구성 요소는 설명하지 않는다고 명시되어 있다.. 
"The Arm architecture does not specify the exact form of the TLB structures for any design."

일반적인 경우 TLB 는 VPN(Virtual Page Number) to PFN(Page Frame Number) 이므로 VPN, PFN 필드로 구성되어 있을 것이고 
armv8 같은 경우 아래의 ASID, VMID 와 같이 그 외의 정보들을 담는 필드가 따로 있을 것이다.
VPN
PFN
Other Fields

## TLB 동작방식
아래처럼 한눈에 설명이 가능한 그림이 wiki에서 확인 가능하다.
https://user-images.githubusercontent.com/50063698/99901806-c4332900-2cfc-11eb-9934-f00e24d67420.png
Virtual Address 가 주어졌을 때 그 VPN 으로 TLB 에서 확인한 후 TLB hit 시에 PFN 을 얻어 VA의 offset 을 통해 최종 PA에 접근한다.
TLB miss 발생 시, Page table walking 하여 PFN 을 얻고 VA의 offset 으로 최종 PA 에 접근한다.
그 후에 해당 내용을 TLB 에 저장한다.


## ASID, VMID

문맥 전환시에 TLB 유지 비용 감소를 위해 일부 변환 체계(translation regimes) 에서의 검색(lookups)은 ASID 혹은 ASID & VMID 를 사용할 수도 있다.

### ASID
Address Space Identifier(ASID)는 특정 프로세스의 page들을 식별가능한 식별자로 사용된다.
그러므로 current process 변경이 발생하더라도 process 의 ASID 는 서로 다르기 때문에 TLB invalidate 을 안 해도 된다.
또한 Stage 1에서 TTBR0_ELx, TTBR1_ELx 은 유효 ASID 필드가 있고 TCR_ELx.A1 은 이들 중 어떤 것이 현재 ASID 인지 결정한다.
TCR_EL1.A1, bit [22]
```
Selects whether TTBR0_EL1 or TTBR1_EL1 defines the ASID. The encoding of this bit is:
0b0 TTBR0_EL1.ASID defines the ASID.
0b1 TTBR1_EL1.ASID defines the ASID.
This field resets to an architecturally UNKNOWN value.
```

### VMID
virtual machine identifier(VMID) 는 EL2 enable 상태에서 Secure or Non-secure EL1&0 변환 체계에서 쓰이는 것으로,
현재 virtual machine 을 식별하는 식별자이다. 그것은 자신의 독립적인 ASID 를 갖는다.
그러므로 VM 간의 문맥전환이 발생하더라도 VM들은 각자의 VMID 를 갖고있으니 TLB invalidate 을 안 해도 된다.
VTTBR_EL2.VMID 가 현재의 VMID 값을 지닌다.


## TLB maintenance instructions
 TLB 관련 명령은 invalidate 밖에 없으며 아래와 같이 제공된다.

```
• Invalidate all entries in the TLB.
• Invalidate a single TLB entry by ASID for a non-global entry.
• Invalidate all TLB entries that match a specified ASID.
• Invalidate all TLB entries that match a specified VA, regardless of the ASID.
• Invalidate all TLB entries within a range of addresses.
```

각 명령은 해당 명령을 수행하는 코어에만 한정될 수도 있고 동일 shareability 를 갖는 코어들에 적용될 수도 있다.
e.g ) 아래 명령은 Inner Shareable 이므로 동일 클러스터의 모든 도메인들에게 적용되며 
VMID 에 해당하는 TLB 엔트리를 invalidate 한다
VMALLE1IS
TLB invalidate by VMID, EL1, Inner Shareable.



## ref
https://developer.arm.com/documentation/den0024/a/The-Memory-Management-Unit/The-Translation-Lookaside-Buffer
DDI0487E_a_armv8_arm.pdf - 2632p
