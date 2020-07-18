## Spring Batch

### 배치 어플리케이션이란?

배치(batch)는 :bat: 일반적으로 **일괄처리** 라는 뜻을 가지고 있으며, 주기적인 일을 실행해야 할 때 제작하는 어플리케이션. 

`단발성` 으로 대용량의 데이터를 처리하기 위한 목적으로 활용. 

그렇기 때문에, 실제 비즈니스:business_suit_levitating: 어플리케이션에도 이 **배치(batch)** 를 고려하면서. 개발에 신경써야 되는 부분이 많다. 

배치 어플리케이션은 5가지 속성이 존재하는데,

1. 대용량 데이터 - 대량의 데이터를 가져오거나, 전달하거나, 계산하는 처리 과정이 꼭 필요함 :white_check_mark:
2. 자동화 - **사용자의 개입 없이** 실행되어야 함 :white_check_mark:
3. 견고성 - 잘못된 데이터를 충돌/중단 없이 실행되어야 함 :white_check_mark:
4. 신뢰성 - 무엇이 잘못되었는지 추적할 수 있어야함 + `Monitoring` , `Alaram` :white_check_mark:
5. 성능 - 지정한 시간안에 처리를 완료하거나 다른 어플리케이션을 방해하지 않도록 수행 :white_check_mark:



### Spring Batch?

DI, AOP, 서비스 추상화 등 Spring :seedling: 에  관련된 3대요소를 모두 사용할 수 있으며,  

크게 **Reader** 와 **Writer** 로 구성되어 있다. 

* Reader: 데이터를 읽는 모듈
* Writer: 데이터를 쓰는 모듈

따라서 전체 흐름은 크게 3단계로 이루어져 있습니다. 

1. 읽기(Read): 데이터 저장소(DB)에서 특정 데이터 레코드를 읽는다 :book:
2. 처리(processing): 원하는 방식으로 데이터를 가공한다 :office:
3. 쓰기(write): 데이터를 다시 저장소(데이터베이스)에 저장한다 :pen:



### Spring Batch 사용시 주의사항

* 배치사용시에는 반드시 I/O 사용을 최소화해서 해야함
  * 안그러면, 데이터베이스 커넥션에 대한 부담과 네트워크 비용이 커짐
* 배치가 돌아가는 동안 기존에 있던 API 서버가 영향을 받지 말아야함
* 스프링 부트는 배치 스케쥴러를 제공하지 않으므로 **crontab** 명령을 사용하는 방법이 있겠지만, 하지말자
  * 보통 쿼츠(Quartz) 프레임워크를 같이 사용하는 것이 일반적
  * 하지만 Batch를 Container에 띄울거라면... `k8s` 에 있는 `CronJob` 으로 띄워도 괜찮지 않을까?



### Spring Batch 객체

<div>
  <img src="img/batch-obejct-relrationship.png" text-align:"center"/>
</div>
Spring Batch 에서는 기본적으로 batch 일을 처리한다는 개념의 **Job** 이 존재합니다. 

해당 **Job** 에서는 여러 Step을 밟을 수 있으며, Job이 여러개의 Step을 처리한다 라고 이해하면 좋을 것 같아요~!  

한번 구현하러 가보면~. 

```java
@Log4j2
@Configuration
@RequiredArgsConstructor
public class SimpleJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob")
                .start(simpleStep1())
                .build();
    }

    @Bean
    private Step simpleStep1() {
        return this.stepBuilderFactory.get("simpleStep1")
                .tasklet((contribution, chunkContext) -> {
                    log.info("=======Step 1========");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
```

 Job이라는 항목아래에 Step이라는 객체가 생성되는 것을 볼 수 있습니다~. 

그러면 도대체 :briefcase: `tasklet` 이라는 것은 무엇이냐?  

한번 그림을 통해 알아보도록 하죠~. 

<div>
  <img src="img/job-step.png" text-align="center" />
</div>

이렇게 그림으로 보면 Tasklet과 그 외 친구들(Reader, Processor, Writer)는 다른 친구들 처럼 보입니다. 

하지만, 이들은 다 똑같은 친구들 :person_frowning:  입니다.  

**Reader, Processor, Writer** 들이 모두 작업이 끝나면 반드시, **Tasklet** 을 통해서 마무리를 지어야 합니다!  






### 참고

* [Spring의 갓이신 창천향로님](https://jojoldu.tistory.com/324)
* [Spring의 대명사이신 cheese10yun 님](https://cheese10yun.github.io/spring-batch-basic/)

* [창천향로님의 Spring Batch 구현해보기](https://jojoldu.tistory.com/325?category=902551)