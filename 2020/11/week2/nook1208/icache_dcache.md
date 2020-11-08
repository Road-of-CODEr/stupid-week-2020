ARMv8-A architecture icache, dcache 정리

- Cache Level
L1 Cache : Core 별 cache이며 icache, dcache 분리되어 존재
L2 Cache : 클러스터마다 코어들이 공유하는 cache
L3 Cache : 클러스터들 사이에서 공유되는 외부 L3 cache. (option)
그 다음으로 Bus 가 있고 그 다음은 Main Memory 이다.
일반적으로 ARMv8-A 아키텍쳐의 프로세서들은 보통 2개 이상의 캐시 레벨로 구성된다.


- PoC
Point of Coherency 는 특정 주소에 대해 다른 코어들과 메모리에 접근이 가능한 DSP, DMA 디바이스들 등 모든 옵저버가 동일한 메모리 사본을 볼 수 있도록 보장한다. SMP 코어에서 데이터 캐시 간의 일관성은 캐시 일관성 하드웨어에 의해 자동으로 처리가 되어도 그 외의 메모리 접근 가능한 디바이스들과의 일관성을 위해 PoC 캐시 조작명령을 사용한다.
PoC는 코어 별 dcache 에 대해 일관성을 관리하는 것으로, icache 에는 관련 명령이 지원 안 된다.

- PoU
Point of Unification 은 코어내에 존재하는 cache 들의 일관성을 위한 것으로, 코어에서 icache, dcache, tlb 등이 동일한 메모리 사본을 볼 수 있도록 보장한다. icache, dcache 가 분리되어 있는 하버드 캐시 타입에서 두 cache의 일관성을 위해 PoU 를 사용한다.
자가 수정 코드 구현 시 코드 변경 후 재사용하기 위해 해당 주소에 대한 dcache 를 PoU 지점으로 클린하고 icache, tlb 를 무효화해서 일관성을 맞춘다고 함.
근데 여기서 instruction 을 바꾸었는데 왜 다른 cache 들까지 무효화하는지에 대한 의문이 있는데, 
잘 생각해보면 원래 data 가 있던 주소에 코드가 저장되게 되면 dcache 에 고스란히 남아있는 동일 주소의 data 는 재사용될 가능성이 있다. 그러므로 dcache 무효화가 필요하다.
tlb 또한 비슷한 맥락이다. data가 있던 주소영역은 아마도 페이지 테이블 속성이 executable 이 아닐지도 모른다. 허나 동일 주소에 명령어가 저장되면 executable 속성을 주게 될 것이고 이때 tlb 또한 무효화가 되어야 해당 코드영역이 실행될 때 permission fault 가 발생하지 않을 것이다.



QnA )
- icache 엔 dirty bit 가 없나? 
없음. 
그래서 armv8-a 의 icache 관련 명령 중 clean 에 대한 명령은 없고 전부 invalidate 에 대한 명령어만 존재한다.
아래에서 IC 관련 명령들 참고
DDI0487E_a_armv8_arm.pdf - 516p 


- i/dcache 는 exception level 별로 따로 clean / invalidate 을 해줘야 함?
그렇진 않고 다만 EL0  에서 사용할 수 있는 캐시 관리 명령은 시스템 레지스터의 설정을 통해 사용가능한 명령들의 집합이 정해질 뿐이다. 아래 설명 참고

DDI0487E_a_armv8_arm.pdf - 134p :
B2.4.3  Application level access to functionality related to caches
As indicated in About the Application level programmers’ model on page B1-98, the application level corresponds
to execution at EL0. The architecture defines a set of cache maintenance instructions that software can use to
manage cache coherency. Software executing at a higher Exception level can enable use of some of this
functionality from EL0, as follows:
    When the value of SCTLR_EL1.UCI is 1
    Software executing at EL0 can access:
    • The data cache maintenance instructions, DC CVAU, DC CVAC, DC CVAP, DC CVADP, and DC CIVAC.
    See The data cache maintenance instruction (DC) on page D4-2483.
    ...



Ref )
https://parksb.github.io/article/29.html
DDI0487E_a_armv8_arm.pdf
코드로 알아보는 ARM 리눅스 커널(2판)
