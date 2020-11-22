## tl;dr

- 기본적인 'pseudo-lock'  도입
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

그리고 stdout 을 사용해서 매번 출력하는 방식을 활용했었는데, 실제 커널에서 동작하게 만드려면 stdout 은 물론 사용하지 못 하고 특정 address 섹션을 정해놓고 원형 큐 방식으로 로깅하여 출력해야한다. 
이때 사용되는 인스턴스는 Global 로 선언되어야 추가적인 인스턴스 생성 없이 필요 없어지고 현재 로깅이 어느 address 영역까지 되었는지 등의 상태를 확인할 수 있다.
또한 Global 인스턴스가 생성되면 여러 코어들간의 동시성이 보장되어야만 한다. 허나 rust 에서 static mut 으로 선언된다면 항상 unsafe 하게 동작한다.
그러므로 우선 락이 구현되어야 하고, 락을 사용해서 Global Data structure 를 safe 하게 동작시켜야 한다. 

그리고 pseudo-lock 구현부에서 클로저를 사용하기 때문에 먼저 클로저 공부를 해야만 한다...
https://rinthel.github.io/rust-lang-book-ko/ch13-01-closures.html

## 클로저 간단 정리

Studying...

