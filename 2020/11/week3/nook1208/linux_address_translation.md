
아래는 linux v4.6 기반으로 된 사진.
(주의 kernel 4.19 -> 5.4  로 오면서 1:1 매핑영역이 bottom 으로 변경되었다.)

https://user-images.githubusercontent.com/50063698/99139117-6443df00-2679-11eb-8b94-e3e11567d4c6.jpg

위 그림과 같이 커널 가상주소영역의 상위 256 G 는 전체 DRAM 영역 매핑에 사용되며 1:1 매핑이라 offset 만 알아도 va to pa 변환이 가능하다.
커널에서 va->pa 변환은 virt_to_phys 로 아래와 같이 가능하다.
vmalloc 으로 매핑된 영역은 저걸 쓰면 안 되고 직접 at instruction 을 통해 변환해야 한다.

```
/*
 * Physical vs virtual RAM address space conversion.  These are
 * private definitions which should NOT be used outside memory.h
 * files.  Use virt_to_phys/phys_to_virt/__pa/__va instead.
 */
#define __virt_to_phys(x) ({						\
	phys_addr_t __x = (phys_addr_t)(x);				\
	__x & BIT(VA_BITS - 1) ? (__x & ~PAGE_OFFSET) + PHYS_OFFSET :	\ // VA_BITS(39) -1 == 38 이며 va의 38번째 bit 가 1일 경우 커널 가상주소의 상위 256G 를 가리킨다.
				 (__x - kimage_voffset); })               

#define __phys_to_virt(x)	((unsigned long)((x) - PHYS_OFFSET) | PAGE_OFFSET)
#define __phys_to_kimg(x)	((unsigned long)((x) + kimage_voffset))
```

PAGE_OFFSET : 커널의 가상 주소 시작 주소가 컴파일 타임에 결정된다.
PHYS_OFFEST : 물리 메모리의 시작주소가 담긴다. 이 주소는 런타임 시 arm64_memblock_init() 함수에서 인식한 DRAM의 시작 물리 주소다.
kimage_voffset :  커널 이미지의 가상 주소와 물리 주소간의 offset 값 

https://user-images.githubusercontent.com/50063698/99139142-8fc6c980-2679-11eb-9f86-d69b119b5beb.png

ref )
https://git.kernel.org/pub/scm/linux/kernel/git/stable/linux.git/tree/arch/arm64/include/asm/memory.h/?h=v4.6
http://jake.dothome.co.kr/2019/08/ 
