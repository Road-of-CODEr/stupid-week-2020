## ARMv8 PSTATE

PSTATE 혹은 process state 은 일종의 프로세스 상태 정보에 대한 추상화로, 프로세스 상태 정보를 나타낸다.
PSTATE 에 존재하는 많은 필드들에 대한 연산들이 제공되며 각 필드는 AArch32 , AArch64 전용으로 사용되거나
그 둘의 공통 속성으로 사용된다.
## PSTATE Fields

특정 feature enable 이 필요한 필드들은 설명에서 제외하였다 (e.g. UAO, MemTag, BTI..)

### The Condition flags

|Name|Description|
|------|-----|
| N |연산 결과가 음수인 경우 설정됨 |
| Z |연산 결과가 0 인 경우 설정됨 |
| C | [캐리](https://koodev.wordpress.com/2015/09/30/%EC%BA%90%EB%A6%AC%EC%99%80-%EC%98%A4%EB%B2%84%ED%94%8C%EB%A1%9C%EC%9A%B0/)가 발생했을 경우 설정됨|
| V |[오버플로](https://koodev.wordpress.com/2015/09/30/%EC%BA%90%EB%A6%AC%EC%99%80-%EC%98%A4%EB%B2%84%ED%94%8C%EB%A1%9C%EC%9A%B0/)가 발생했을 경우 설정됨 |

### The Execution state controls


|Name|Description|
|------|-----|
| SS | Software Step bit. <br> Software Step 이란 프로세서(PE)의 단일 명령어를 수행가능케 해주는 것으로,  T32와 같은 디버거가 라이브 디버깅 할때 사용될 것으로 간주됨.|
|IL| Illegal Execution state bit. <br> 이 비트가 설정되면 어떠한 명령의 시도도 Illegal Execution State 익셉션을 유발함 <br> 해당 예외를 유발하는 조건들은 굉장히 많으니 직접 ARMv8 문서에서 확인하도록..|
|nRW| Current Execution state. 이 비트가 0 이면 AArch64, 1이면 AArch32 임을 나타낸다.|
|EL|Current Exception level. |
|SP|Stack pointer register selection bit. <br>0 이면 SP_EL0, 1 이면 SP_ELx 를 스택 포인터로 사용한다.|

### The exception mask bits


|Name|Description|
|------|-----|
|D|이 비트가 설정되면 디버깅 익셉션(WatchPoint, BreakPoint, Software Step exception 등) 발생을 막는다.<br> reset 혹은 AArch64 로의 익셉션 발생 시에 이 비트가 설정된다.|
|A,I,F| 비동기 예외 마스크 비트로, 해당 비트가 설정되면 그에 맞는 익셉션 발생을 막는다: <br> A    SError interrupt mask bit. <br>I    IRQ interrupt mask bit. <br> F    FIQ interrupt mask bit.|
## Accessing PSTATE Fields

AArch64 일때 아래의 PSTATE field 들은 특수목적 레지스터들을 통해 MRS, MSR 명령으로 직접 접근이 가능하며 
그 외의 필드들엔 직접 접근이 불가능하다.

| Special-purpose register | PSTATE fields|
|---|---|
| NZCV | N, Z, C, V|
| DAIF | D, A, I, F |
| CurrentEL| EL|
|SPSel |SP|

NZCV, DAIF 필드들은 EL0 에서도 접근이 가능하며 그 외의 필드들은 EL1 이상에서만 접근 가능하다.
익셉션 발생 시에 PSTATE 가 갱신되며 특정 비트들이 SPSR_ELn 레지스터에 복사된다.
익셉션에서 복귀 시에 SPSR_ELn 레지스터가 PSTATE 로 복사된다.
자세한 내용은 추후 AArch64익셉션 핸들링 파트에서 다룰 예정이다.

