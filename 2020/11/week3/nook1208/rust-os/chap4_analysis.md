rust-os | chap4 - Zero_overhead_abstraction 분석

이번 챕터에선 어셈블리로 작성되었던 부팅 초반 코드를 cortex-a 크레이트로 대신 함으로써 rust 코드로 바꾸는 작업을 함.

cpu.S 분석 )
아래는 러스트 코드로 변경될 cpu.S 어셈블리 코드인데 이전에 분석 못 하고 넘어갔던 부분을 다시 보자

src/_arch/aarch64/cpu.S
```
  1 .section ".text._start"                                                                                            
  2                              
  3 .global _start
  4                              
  5 _start:
  6     mrs     x1, mpidr_el1   // Read Multiprocessor Affinity Register
  7     and     x1, x1, #3      // Clear all bits except [1:0], which hold core id
  8     cbz     x1, 2f          // Jump to label 2 if we are core 0
  9 1:  wfe                     // Wait for event
 10     b       1b              // In case an event happened, jump back to 1
 11 2: 
 12     ldr     x1, =_start     // Load address of function "_start()"
 13     mov     sp, x1          // Set start of stack to before our code, aka first
 14     ¦   ¦   ¦   ¦   ¦   ¦   // address before "_start()"
 15     ¦   ¦   ¦   ¦   ¦   ¦   // but, why should we do that? <-- 이 부분
 16     bl      runtime_init    // Jump to the "runtime_init()" kernel function
 17     b       1b              // We should never reach here. But just in case,
 18     ¦   ¦   ¦   ¦   ¦   ¦   // park this core aswell
```
코드 라인 13 에서는 _start 함수의 주소를 x1 에 저장하고 그 값을 sp (stack pointer) 레지스터로 가져오는데 
이는 스택 용도로 사용될 메모리 위치를 설정해주는 부분임.

src/bsp/raspberrypi/link.ld
```
SECTIONS
{
    /* Set current address to the value from which the RPi starts execution */
    . = 0x80000;

    .text :
    {
        *(.text._start) *(.text*)
    }

    .rodata :
    {
        *(.rodata*)
    }
...
```
이제 sp 를 _start 함수의 주소로 사용한 이유를 알아보자
aarch64 아키텍처의 stack type 은 full descending 이므로, 스택이 아래로 자란다.
그리고 링커 스크립트를 보면 아래와 같이 ".text._start" 심볼 주소값은 0x8_0000 이고, 해당 바이너리의 모든 코드 및 데이터의 주소는 0x8_0000 이상의 주소영역에 존재하게 된다.
즉 _start 주소를 기점으로 스택은 아래로 자라고, 모든 코드와 데이터는 위로 자란다(?) 그러니 스택 주소를 _start 로 해주면 스택 주소 영역이 corruption 날 일은 없다는 것.


#[naked] )
```
#[naked]
#[no_mangle]
pub unsafe extern "C" fn _start() -> ! {
    use crate::runtime_init;

    // Expect the boot core to start in EL2.
    if bsp::cpu::BOOT_CORE_ID == cpu::smp::core_id() {
        SP.set(bsp::memory::BOOT_CORE_STACK_START as u64);
        runtime_init::runtime_init()
    } else {
        // If not core0, infinitely wait for events.
        wait_forever()
    }
}
```

https://github.com/nox/rust-rfcs/blob/master/text/1201-naked-fns.md
#[naked] 속성은 해당 함수의 프롤로그, 에필로그가 필요없을 때 사용된다.
프롤로그란 함수가 호출될 시에 함수 내에서 스택 프레임을 설정하는 작업이고,
에필로그란 함수가 동작을 마치고 이전 컨텍스트로 돌아가기위해 스택에 저장된 값들을 통해 각종 레지스터를 복원하는 작업을 의미한다.
간단하게 생각하면 이전 함수로 돌아가기 위한 작업이 두 단계로 나뉘는 것으로 보면 된다.
스택에 각 레지스터를 저장해두는 작업, 동작이 끝나고 그걸 복원하는 작업. 

이 두 단계를 스킵하는 경우는 함수 내에서 해야할 동작만 수행하고 이전 컨텍스트로 돌아갈 필요가 없는 경우에 속할 것 같다.
예를들어 호출되자마자 바로 다른 함수만 call 하고 끝나는 interrupt handler 나, 
지금 위의 코드와 같이 부팅 시 최초로 불리고 끝나버리는 함수가 그 경우가 되겠다.


