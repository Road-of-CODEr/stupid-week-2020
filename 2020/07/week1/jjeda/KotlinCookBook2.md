## 6장


## 7장

#### 7.1 apply로 객체 생성 후에 초기화하기
```kotlin
inline fun <T> T.apply(block: T.() -> Unit): T 
```
#### 7.2 부수 효과를 위해 also 사용하기
```kotlin
public inline fun <T> T.also(block: (T) -> Unit): T 
```

#### 7.3 let 함수와 엘비스 연산자 사용하기
```kotlin
public inline fun <T, R> T.let(block: (T) -> R): R 
```
let 함수는 컨텍스트 객체가 아닌 블록의 결과를 리턴한다.
map 처럼 마치 컨텍스트 객체의 변형처럼 동작함

```kotlin
fun processNullableString(str: String?) =
    str?.let {
      when {
        it.isEmpty() -> "Empty"
        it.isBlank() -> "Blank"
        else -> it.capitalize()      
      }     
    } ?: "Null"
```

let 함수를 이용하여 임시변수에 할당하지 않고 바로 처리할 수 잇음
```kotlin
val resultList = numbers.map { it.length }.filter { it >3 }
pritnln(resultList) // 임시 변수 할당 후 사용
```

```kotlin
numbers.map { it.length }.filter { it >3 }.let {
  pritnln(it) // 더 많은 작업 가능 
}
```
 
#### 10.1 use 로 리소스 관리하기
- try-with-resources 를 사용할 수 없음
- useLines 를 사용

```kotlin
inline fun <T> File.useLines(
  charset: Charset = Charsets.UTF_8,
  block: (Sequence<String>) -> T
): T
```
- 첫 번째 선택적 인자는 문자집합
- 두 번째 인자는 파일의 줄을 나타내는 Sequence를 제네릭으로 매핑하는 람
다

#### 11.2 반복적으로 람다 실행하기 
```kotlin
fun main(args: Array<STring>) {
  repeat(5) {
      println("Counting: $it") // 0 1 2 3 4 
  }
}
```

## 13장 코루틴

##### 13.1 코루틴 빌더
##### 13.2 async/await를 withContext로 변경
##### 13.3 디스패처 사용
##### 13.4 자바 스레드 풀에서 코루틴 실행
##### 13.5 코루틴 취소
##### 13.6 코루틴 디버깅



