# ARMv8 Exception Handler 정리
- **What's Exception ?**  
ARM 에서 Exception 이란 소프트웨어의 실행 흐름을 가로채는 동작을 의미한다.   
일반적으로 이러한 동작을 인터럽트라고 부르지만 ARM 에서는 인터럽트도 익셉션 중의 하나이다.

## Exception Type
- **Exception generating instructions**  
  - **SVC(Supervisor Call):** User mode (EL0) 에서 OS 서비스(EL1) 요청 시 사용됨  
  - **HVC(Hypervisor Call):** Guest OS 에서 Hypervisor(EL2) 서비스 요청 시 사용됨  
  - **SMC(Secure Monitor Call):** Normal world 에서 Secure World 서비스를 요청할 때 사용됨  
- **Abort Exception**  
  - **Instruction Abort**    
  instruction fetch 가 실패할 경우 Instruction Abort 가 발생한다.    
  명령어 fetch 의 문제가 발생할 경우 파이프라인 내에 abort 상태로 기록되어 있다가    
  코어가 실제 해당 명령을 실행할 때  Instruction Abort 가 발생한다.  
  - **Data Abort**    
  데이터 접근이 실패할 경우 Data Abort 가 발생한다. 데이터 로드 혹은 스토어 실행이 실패할 경우 발생한다.    
  MMU의 메모리 접근이 실패할 경우에도 Abort 가 발생하며 리눅스는 이 MMU abort 를 이용한 Demand Paging 을 구현한다.  
- **Reset Exception**    
코어에 리셋이 입력되면 Reset Exception 이 발생한다. 리셋 익셉션 발생 시 코어는 리셋 벡터로 점프한다.    
이때 리셋 벡터 설정에 사용되는 레지스터는 RVBAR_ELn 레지스터이며 n 은 현재 프로세서에서 구현된 가장 높은 Exception Level 의 번호다.    
리셋 익셉션은 전원을 켠 후 코어를 초기화하는 코드를 실행하는 데 사용된다.  
- **Interrupt Exception**    
ARM 아키텍처의 인터럽트는 IRQ, FIQ 로 나뉘고 FIQ 의 우선순위가 IRQ 보다 높다.  
  
## Synchronous / Asynchronous Exception  
|  | 동기 익셉션| 비동기 익셉션 |  
|--|--|--|  
|**익셉션 발생 원인**| 인스트럭션 실행 또는 실행 시도의 결과로 발생 | 인스트럭션의 실행과 무관|  
|**익셉션 정보** | 리턴 주소는 익셉션을 발생시킨 인스트럭션 | 리턴 주소가 익셉션 원인에 대한 정보를 제공하지 않을 수 있음|  
|**인터럽트 소스**|- MMU의 Instruction, Data Abort <br> - PC, SP 의 정렬 위반 <br> - 동기 외부 Abort<br> - 할당되지 않은 / 허용되지 않은 인스트럭션 실행<br> - 디버그 익셉션 | - IRQ<br> - FIQ<br> - SError |   
  
- **동기 익셉션**    
동기 익셉션은 익셉션의 원인을 ESR_ELn(Exception Syndrome Register)레지스터에서 확인 가능하다.    
FAR_ELn(Fault Address Register)는 모든 동기 인스트럭션, 데이터 abort, 정렬 위반에 대한 fault 를 유발한 가상주소를 저장하고 있다.    
만약 EL2 가 구현되어 있다면 HPFAR_ELn(Hypervisor IPA Fault Address Register) 레지스터를 통해 fault 를 유발한 가상주소의 IPA 를 알 수 있다.    
위 레지스터들은 주로 동기 익셉션 발생 시 디버깅에 유용한 대표적인 레지스터들이다.    
EL2(Hypervisor)나 EL3(Secure Monitor) 를 구현한 시스템에서 동기 익셉션들은 현재 또는 상위 익셉션 레벨에서 처리된다.  
  
- **비동기 익셉션**    
비동기 익셉션은 하이퍼바이저나 보안 커널이 다룰 수 있도록 상위 익셉션 레벨로 라우팅될 수 있다.    
SCR_EL3 레지스터는 EL3 로 라우팅될 익셉션을 지정하고, 마찬가지로 HCR_EL2 는 EL2 로 라우팅 될 익셉션을 지정한다.    
IRQ, FIQ 및 SError의 라우팅을 개별적으로 제어할 수 있다.  
  
## Exception Handling  
### Exception 발생 시  
**1. SPSR_ELn 업데이트**    
여기서 n 은 익셉션을 핸들링하는 익셉션 레벨이며 SPSR_ELn 은 익셉션이 끝날 때 올바르게 리턴하는 데 필요한 PSTATE 정보를 저장하기 위해 갱신된다.    
**2. PSTATE 업데이트**    
PSTATE 가 새 프로세서 상태를 반영하도록 갱신됨.    
**3. 복귀 주소를 ELR_ELn 에 저장**    
익셉션 타입에 따라 익셉션 처리가 끝나고 돌아갈 적절한 복귀 주소가 ELR_ELn 에 저장된다.    
예를 들어 SVC 명령으로 인한 익셉션인 경우 SVC 다음 주소로 복귀 주소가 설정되고,    
익셉션을 발생시킨 명령의 경우는 그 원인에 대한 조치가 끝난 후 다시 해당 명령을 수행하기위해 설정되기도 한다.    
**4. Synchronous 혹은 SError 일 때 ESR_ELn 에 발생 원인 기록**    
  
### Exception 처리 후 ERET 명령 실행 시  
**1. SPSR_ELn 을 PSTATE 로 복원**    
**2. ELR_ELn 에서 PC 를 복원해 복귀**  
  
## AArch64 Exception Vector Table  
익셉션이 발생하면 프로세서는 익셉션에 대응되는 약속된 위치의 익셉션 핸들러 코드를 수행한다.    
이 핸들러가 저장된 메모리 영역을 익셉션 벡터라고 부른다. ARM 아키텍쳐에서는 EL0를 제외한 각 익셉션 레벨에 따른 벡터 테이블을 갖고있다.    
각 테이블의 주소는 VBAR_ELn(Vector Based Address Register) 에 저장된다. (1 <= n <= 3)    
아래는 분기 조건에 따른 벡터 테이블들의 주소이다.  
  
![image](https://user-images.githubusercontent.com/50063698/101241869-4f6cdf80-373d-11eb-9fe7-8b24c07209e3.png)  
  
### Reference  
코드로 알아보는 ARM 리눅스 커널    
DDI0487E_a_armv8_arm.pdf  
