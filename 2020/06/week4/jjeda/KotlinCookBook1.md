## 2장 - 코틀린기초

#### 2.3 자바를 위한 메서드 중복
- `@JvmOverloads` 애노테이션을 해당 함수에 추가
  - 자바에서는 default parameter 를 지원하지 않으므로 모든 파라미터를 넘겨주어야한다
  - constructor 키워드 명시
  - super 호출 x
  
## 3장 - 코틀린 OOP
코틀린의 기본 접근이 public 임에도 캡슐화를 지킬 수 있는이유
- backing field: 게터와 세터를 정의할 수 있다.


## 4장 - 함수형 프로그래밍

#### 4.1 알고리즘에서 fold 사용하기
```kotlin
fun sun(vararg nums: Int) = nums.fold(0) { acc, n -> acc + n }
```
첫 번째 인자 값으로 초기화되고 n 변수는 nums 컬렉션의 각 원소를 받고, 람다 결과가 새로운 acc 값이 됨.

#### 4.2 reduce 함수를 사용해 축약
- 초기값을 설정하지 않고 싶을 때는 fold 대신 reduce 를 사용
```kotlin
fun sun(vararg nums: Int) = nums.reduce { acc, n -> acc + n }
```
첫 번째인자가 초기값으로 초기화 됨

## 5장 - 컬렉션

#### 5.4 컬렉션에서 맵 만들기
`associate()` - 키만 가지고 있을 때 값과 매핑하는 함수
 ```kotlin
val map = keys.associate { it to it.toString() }
val map = keys.associateWith { it.toString() }
```

#### 5.5 컬렉션이 빈 경우 기본값 리턴하기
`ifEmpty` - 코틀린에도 `Optional<T>` 을 지원하지만 `ifEmpty` 함수를 사용해 특정한 값을 리턴하는 방법이 더 사용하기 쉽다.

#### 5.7 컬렉션을 윈도우로 처리하기
- windowed: 정해진 간격으로 컬렉션을 따라 움직이는 블록
  - size: 윈도우 사이즈
  - step: 전진할 원소의 개수(default: 1)
  - partialWindows: 마지막 부분이 윈도우 사이즈만큼 없을 때 그대로 유지할지 여부(default: false)
- chunked: 같은 크기로 나누는 함수
  - size == step 인 windowed 함수
  
#### 5.9 다수의 속성으로 정렬하기
- sortedWith: `Comparator`를 인자로 받는다
- comparedBy: `Comparator`를 생성한다
  - `Comparable` 속성을 추출하는 선택자 목록을 제공
  - compareBy 함수가 차례차례 정렬에 사용되는 `Comparator`를 생성

```kotlin
val sorted = golfers.sortedWith(
  compareBy({ it.score }, { it.last }, { it.first })
)
```

```kotlin
val comparator = compareBy<Golfer>(Golfer::score)
    .thenBy(Golfer::last)
    .thenBy(Golfer::first)

golfers.sortedWith(comparator)
```  

#### 5.10 사용자 정의 이터레이터 정의하기
  - `next`, `hasNext` 함수를 모두 구현한 이터레이터를 리턴하는 연산자 함수 정의

```kotlin
interface Iterator<out T> {
  operator fun next(): T
  operator fun hasNext(): Boolean
}
```
`Iterable` 인터페이스에 abstract operator function 으로 `iterator`가 있다.
```kotlin
class Team(val name: String, val players: MutableList<Player> = mutableListof()): Iterable<Player> {
  override fun iterator(): Iterator<Player> = players.iterator()
}
val team = Team("Warriors")
team.map { it.name }.joinToString() // 순회할 수 있다.

```
