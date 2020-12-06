## tl;dr

- 클로저를 활용한 기본적인 'pseudo-lock'  도입 
- OS 의 동기화 기본 요소 및 Safe 한 Global data Structure 도입


이전에 로그 출력에 사용했던 방식은 QEMUOutput 인스턴스를 항상 새로 만들어서 사용했다.
console.rs 
```
4 pub fn _print(args: fmt::Arguments) {
5     pub use core::fmt::Write;
6     bsp::console::console().write_fmt(args).unwrap();
7 }
```
print.rs
```
18 pub fn console() -> impl console::interface::Write {
19     QemuOutput                                                                                                                                                                                                                           
20 }
```

그리고 stdout 을 사용해서 매번 출력하는 방식을 활용했었는데, 실제 커널에서 동작하게 만드려면 stdout 은 물론 사용하지 못하고  
특정 address 섹션을 정해놓고 원형 큐 방식으로 로깅하여 출력해야한다. 
이때 사용되는 인스턴스는 Global 로 선언되어야 추가적인 인스턴스 생성 없이 필요 없어지고 현재 로깅이 어느 address 영역까지 되었는지 등의 상태를 확인할 수 있다.  
또한 Global 인스턴스가 생성되면 여러 코어들간의 동시성이 보장되어야만 한다. 허나 rust 에서 static mut 으로 선언된다면 항상 unsafe 하게 동작한다.
그러므로 우선 락이 구현되어야 하고, 락을 사용해서 Global Data structure 를 safe 하게 동작시켜야 한다.   

그리고 pseudo-lock 구현부에서 클로저를 사용하기 때문에 먼저 [클로저](https://rinthel.github.io/rust-lang-book-ko/ch13-01-closures.html) 공부를 해야만 한다...



## 클로저 간단 정리

[클로저](https://rinthel.github.io/rust-lang-book-ko/ch13-01-closures.html)란 환경을 캡처할 수 있는 익명함수로, 이를 변수에 저장하거나 다른 변수에 넘길 수도 있다.
실제 사용 예시를 통해 클로저를 알아보자.

### Closure Use Case
```
fn simulated_expensive_calculation(intensity: u32) -> u32 {
    println!("calculating slowly...");
    thread::sleep(Duration::from_secs(2));
    intensity
}

fn generate_workout(intensity: u32, random_number: u32) {
    if intensity < 25 {
        println!(
            "Today, do {} pushups!",
            simulated_expensive_calculation(intensity)
        );
        println!(
            "Next, do {} situps!",
            simulated_expensive_calculation(intensity)
        );
    } else {
        if random_number == 3 {
            println!("Take a break today! Remember to stay hydrated!");
        } else {
            println!(
                "Today, run for {} minutes!",
                simulated_expensive_calculation(intensity)
            );
        }
    }
}
```
위와 같은 코드에서 simulated_expensive_calculation 함수를 단 한번만 호출하도록 하고, 불필요하게 두 번 호출되지 않도록 할 때 클로저를 사용하기에 좋다.
그럼 simulated_expensive_calculation 함수를 클로저로 바꿔서 구현해보자

```
let expensive_closure = |num| {
    println!("calculating slowly...");
    thread::sleep(Duration::from_secs(2));
    num
};
```
simulated_expensive_calculation 함수와 동일한 기능을 하는 클로저가 변수 expensive_closure 에 할당되는 예시이다. 
클로저를 정의하기 위해, 수직의 파이프 (|) 한쌍으로 시작하며, 그 사이에 클로저에 대한 파라미터를 설정한다.
이 클로저 num 이라는 하나의 파라미터를 갖지만 하나 이상의 파라미터를 갖는다면, |param1, param2| 와 같이 콤마로 구분한다.
파라미터들 다음에 클로저의 바디를 포함하는 중괄호가 설정된다. 만약 클로저 바디가 하나의 표현식이라면 중괄호가 없어도 된다.
중괄호 다음에 클로저의 끝에는 let 문을 완성하기 위해 세미콜론이 필요하다.
클로저 바디에서 마지막 줄로부터 반환되는 값인 (num) 은 그것이 호출되었을 때 클로저로 부터 반환되는 값이 된다.
여기서 유의할 점은 let 문은 expensive_closure 가 익명함수, 즉 클로저의 정의를 포함하며, 익명함수를 호출한 결과를 포함하지 않는다는 것이다.



### 클로저 타입 추론과 어노테이션
클로저는 fn 함수처럼 파라미터나 반환값의 타입을 명시하도록 요구하지 않는다. 또한 클로저는 함수와는 다르게 변수에 저장되므로 심볼이 없다. 
이는 누군가 빌드된 자신의 코드를 디셈블해서 보더라도 클로저로 만들어진 구현부는 심볼이 없기 때문에 노출될 일이 없다는 이점이 있다.
또한 클로저는 보통 짧고 임의의 시나리오 보다 좁은 문맥 안에서만 관련이 있으므로 이런 제한된 문맥 안에서만 
컴파일러는 안정적으로 파라미터와 리턴타입을 추론할 수 있으며 이는 대부분의 변수 타입을 추론 할 수 있는 방법과 비슷하다고 한다.
그럼에도 불구하고 타입을 명시하고 싶다면 함수와 상당히 유사한 방식으로 명시가 가능하다.

```
fn  add_one_v1   (x: u32) -> u32 { x + 1 }
let add_one_v2 = |x: u32| -> u32 { x + 1 };
let add_one_v3 = |x|             { x + 1 };
let add_one_v4 = |x|               x + 1  ;
```

위의 네가지 방식의 구현은 모두 동일한 행위를 수행하는 유효한 정의들이다.

클로저 정의는 각 파리미터들과 그들의 반환값에 대해 단 하나의 추론된 구체적인 타입을 갖을 것이다.
만약 아래와 같이 클로저를 두번 호출하는데, 첫번째는 String 을 인자로 사용하고 두번째는 u32 을 사용한다면 에러가 발생한다.

```
let example_closure = |x| x;

let s = example_closure(String::from("hello"));
let n = example_closure(5);
```

```
error[E0308]: mismatched types
 --> src/main.rs
  |
  | let n = example_closure(5);
  |                         ^ expected struct `std::string::String`, found
  integral variable
  |
  = note: expected type `std::string::String`
             found type `{integer}`
```
처음 String 값으로 example_closure 을 호출하면, 컴파일러는 x 의 타입과 클로저의 반환 타입을 String 으로 추론한다.
그래서 이 타입들은 그 다음에는 example_closure 에 있는 클로저에 고정되고, 같은 클로저를 다른 타입으로 사용하려고 할 때 타입 에러를 얻게 된다.  



### 제너릭 파라미터와 Fn 트레잇을 사용하여 클로저 저장하기
아까 보았던 유스 케이스에서 우리는 동일한 함수를 같은 문맥에서 두 번 부르곤 했다.
만약 변수를 하나 새로 만들어서 함수의 결과값을 저장해두면 동일한 함수를 두 번 부를 필요가 없어지긴 하는데, 이 경우 클로저는 더 좋은 접근 방식을 갖고있다.

클로저와 클로저를 호출한 결과값을 갖고 있는 구조체를 만들어서 결과값을 필요로 할 때만 클로저를 호출 하며 
결과값을 캐시에 저장해 두어 우리의 나머지 코드에서 결과를 저장하고 재사용 하지 않아도 되도록 만들 수 있다.

또한 모든 클로저들은 다음 트레잇 중 하나를 구현한다: Fn, FnMut, FnOnce. 
이는 다음 챕터인 환경을 캡처하는 것에 대한 다음 절에서 이 트레잇들의 차이점들에 대해 설명되며 아래 예제에서는 Fn 트레잇을 사용하는 것으로 한다.

```
struct Cacher<T>
    where T: Fn(u32) -> u32
{
    calculation: T,
    value: Option<u32>,
}
```
Cacher 구조체는 제너릭 타입 T 의 calculation 필드를 갖는다. T 에 대한 트레잇 바운드는 Fn 트레잇을 사용하여 그것이 클로저라는 것을 기술할 수 있다.
value 에는 클로저가 수행되지 않았다면 None, 수행 되었다면 그 결과 값을 갖게 될 것이며 반복적으로 해당 클로저가 사용되면 value 를 넘겨줌으로써
두 번 이상 클로저가 수행되지 않도록 한다. 아래는 이를 위한 구현부이다.

```
impl<T> Cacher<T>
    where T: Fn(u32) -> u32
{
    fn new(calculation: T) -> Cacher<T> {
        Cacher {
            calculation,
            value: None,
        }
    }

    fn value(&mut self, arg: u32) -> u32 {
        match self.value {
            Some(v) => v,
            None => {
                let v = (self.calculation)(arg);
                self.value = Some(v);
                v
            },
        }
    }
}
```

최종적으로 구조체를 이용한 클로저를 사용함으로써 아래와 같이 generate_workout 함수는 구현될 수 있다.
```
fn generate_workout(intensity: u32, random_number: u32) {
    let mut expensive_result = Cacher::new(|num| {
        println!("calculating slowly...");
        thread::sleep(Duration::from_secs(2));
        num
    });

    if intensity < 25 {
        println!(
            "Today, do {} pushups!",
            expensive_result.value(intensity)
        );
        println!(
            "Next, do {} situps!",
            expensive_result.value(intensity)
        );
    } else {
        if random_number == 3 {
            println!("Take a break today! Remember to stay hydrated!");
        } else {
            println!(
                "Today, run for {} minutes!",
                expensive_result.value(intensity)
            );
        }
    }
}
```
위와 같이 value 메소드를 원하는 만큼 많이 호출할 수 있고, 전혀 호출하지 않을 수도 있으며, 클로저는 최대 한 번만 수행된다.
다만 문제는 여기서 intensity 가 항상 고정된 값으로 들어오진 않을 것이란 거다. 만약 10으로 intensity 가 들어왔다가 다음에 100 으로 들어온다면
그 다음 클로저의 결과 값은 10 으로 intensity 가 들어왔을때의 결과값과 동일한 값을 반환할 것이다.
이때는 해쉬 테이블을 사용하면 해결이 된다. 클로저의 input 으로 key 값을 받고, 그 값에 따라 결과값을 해쉬 테이블에 저장해두면 
만약 나중에 동일한 key 값이 들어왔을 때 이미 저장되어있던 결과값을 주면 되고, 새로운 key 값이 들어왔을 땐 클로저를 수행해서 결과값을 반환해주면 된다.
마치 DP로 문제를 해결하는 것과 유사한 해결방식인 것이다.


### 클로저로 환경 캡처 하기
클로저는 단순 익명 인라인 함수로만 사용되는 게 아닌 함수가 지니지 못한 기능을 갖는다. 
그게 바로 클로저의 환경 캡처 능력, 즉 환경을 캡처해서 클로저가 정의된 스코프의 변수들을 접근할 수 있다.

```
fn main() {
    let x = 4;

    let equal_to_x = |z| z == x;

    let y = 4;

    assert!(equal_to_x(y));
}
```
위처럼 x 값이 클로저 바디에서 선언이 되지도, 파라미터로 받지도 않았는 데 사용이 가능하다. 이는 함수에서는 불가능한 방식이다.
허나 이렇게 클로저가 정의된 환경에서 캡처된 변수를 클로저 바디에서 사용할 때 그 값을 저장하기 위한 메모리를 사용해야 된다는 점이 오버헤드이다.

클로저는 세가지 방식으로 그들의 환경에서 값을 캡처 할 수 있는데, 함수가 파라미터 를 받는 세가지 방식과 직접 연결 된다: 
소유권 받기, 불변으로 빌려오기, 가변으로 빌려오기. 이것들은 다음과 같이 세개의 Fn 트레잇으로 표현 가능하다:
- FnOnce 는 클로저에서 그것이 정의된 환경 내에 존재하는 변수를 사용하기 위해서 변수의 소유권을 가져야 한다. 이름의 일부인 Once 는 그 클로저가 동일한 변수들에 대해 한번이상 소유권을 얻을수 없다는 사실을 의미하며, 그래서 한 번만 호출 될 수 있다.
- Fn 은 그 환경으로 부터 값들을 불변으로 빌려 온다.
- FnMut 값들을 가변으로 빌려오기 때문에 그 환경을 변경할 수 있다.

우리가 클로저를 만들때, 러스트는 클로저가 환경에 있는 값을 어떻게 사용하는지에 근거 해서 어떤 트레잇을 사용할지 추론 한다.
그러므로 equal_to_x 에서는 x 값을 읽기만 하기 때문에 Fn 트레잇을 사용할 것이다.

만약 클로저가 환경으로부터 사용하는 값에 대해 소유권을 갖도록 강제하고 싶다면, 파라미터 리스트 앞에 move 키워드를 사용할 수 있다.
이 기법은 클로저를 다른 쓰레드로 넘길때 데이터를 이동시켜 새로운 쓰레드가 소유하도록 할때 대부분 유용하다고 한다.
아래는 그 사용 예시이다.

```
fn main() {
    let x = vec![1, 2, 3];

    let equal_to_x = move |z| z == x;

    println!("can't use x here: {:?}", x);

    let y = vec![1, 2, 3];

    assert!(equal_to_x(y));
}
```

```
error[E0382]: use of moved value: `x`
 --> src/main.rs:6:40
  |
4 |     let equal_to_x = move |z| z == x;
  |                      -------- value moved (into closure) here
5 |
6 |     println!("can't use x here: {:?}", x);
  |                                        ^ value used here after move
  |
  = note: move occurs because `x` has type `std::vec::Vec<i32>`, which does not
  implement the `Copy` trait
```

x 의 소유권이 클로저 내로 이동되었는데도 println 에서 x 값에 접근을 하게되어 컴파일 에러가 난 상황이다.
Fn 트래잇 바운드 중 하나를 기술할 때 대부분의 경우, Fn 으로 시작해보면 컴파일러는 클로저 바디에서 무슨일을 하는지에 근거해서 
FnMut 혹은 FnOnce 이 필요한지 알려준다고 한다. 오 역시.. 


### References
[Cell](https://doc.rust-lang.org/std/cell/)  
Shareable mutable containers exist to permit mutability in a controlled manner, even in the presence of aliasing. Both Cell<T> and RefCell<T> allow doing this in a single-threaded way. However, neither Cell<T> nor RefCell<T> are thread safe (they do not implement Sync). If you need to do aliasing and mutation between multiple threads it is possible to use Mutex, RwLock or atomic types.


[UnsafeCell](https://doc.rust-lang.org/std/cell/struct.UnsafeCell.html)  
The UnsafeCell API itself is technically very simple: .get() gives you a raw pointer *mut T to its contents. It is up to you as the abstraction designer to use that raw pointer correctly.

[Self](https://doc.rust-lang.org/reference/paths.html#self-1)  
Self, with a capital "S", is used to refer to the implementing type within traits and implementations.

[Sized](https://doc.rust-lang.org/std/marker/trait.Sized.html)  
Types with a constant size known at compile time.

[const fn](https://doc.rust-lang.org/nightly/edition-guide/rust-next/const-fn.html)  
A const fn allows you to execute code in a "const context."


