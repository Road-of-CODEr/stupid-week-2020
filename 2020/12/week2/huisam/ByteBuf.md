# ByteBuf

## 목차
- [ByteBuf](#bytebuf)
  - [목차](#목차)
  - [기본적인 구조](#기본적인-구조)
    - [작동방식](#작동방식)
    - [Heap Buffer](#heap-buffer)
    - [Direct Buffer](#direct-buffer)
    - [Composite Buffer](#composite-buffer)
  - [바이트 수준 작업](#바이트-수준-작업)
    - [읽기 작업](#읽기-작업)
  - [ByteBufHolder](#bytebufholder)
  - [ByteBufUtil](#bytebufutil)

## 기본적인 구조

ByteBuf 추상클래스와 ByteBufHolder 인터페이스라는 두 컴포넌트를 통해 노출한다
* 사용자 정의 버퍼 형식으로 확장 가능
* 내장 복합 버퍼 형식을 통해 투명한 제로 카피 달성
* 용량을 필요에 따라 확장
* Reader와 Writer 모드로 전환 가능
* 메서드 체인 지원
* 읽기와 쓰기의 고유 인덱스 적용
* 풀링이 지원
* 참조 카운팅이 지원

### 작동방식

작동 방식은 너무나 간단하다  
**16Byte**의 Buffer가 있다고 가정하면, `readerIndex` 와 `writerIndex` 를 적절하게 활용하는 건데요  

1. 데이터를 기록하면 `writerIndex` 가 증가하게 되고,
2. 데이터를 읽을때는 `readerIndex` 가 증가하게 된다!

### Heap Buffer

보조 배열이라고 하는 가장 보편적인 `ByteBuf` 패턴   
JVM Heap 공간에 데이터를 저장하는 방식!  
```java
ByteBuf heapBuf = ...;
if (heapBuf.hasArray()) {
    byte[] array = heapBuf.array();
    int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();
    int length = heapBuf.readableBytes();
    handleArray(array, offset, length);
}
```

### Direct Buffer

위와는 다른 패턴의 Buffer 인데요  
Heap 공간에 데이터를 저장하는 것이 아닌 **Heap 바깥에 저장하는 방식**  
> Direct Buffer는 네트워크 데이터 전송에 이상적이기 때문  

그렇기 때문에 단점은 바로 메모리 할당과 해제에 비용부담이 크다는 것  

### Composite Buffer

정말 간단히 얘기해서는, 여러 개의 `ByteBuf` 들을 조합할 수 있다는 것이다!  
```java
CompositeByteBuf messageBuf = Unpooled.compositeBuffer();
ByteBuf headerBuf = ...;
ByteBuf bodyBuf = ...;
messageBuf.addComponents(headerBuf, bodyBuf);
....
messageBuf.removeCompoent(0);
for (ByteBuf buf : messageBuf) {
    System.out.println(buf.toString());
}
```


## 바이트 수준 작업

`ByteBuf`는 쉽게 말해서 Array와 매우 유사하다  
Array처럼 해당하는 인덱스에 쉽게 접근하고 필요한 경우, `readerIndex` 혹은 `writerIndex` 도 수정해서 마음대로 변경할 수 있다!  

### 읽기 작업

앞서 말했듯이 `readerIndex` 를 통해서 Buffer를 어디까지 읽었는지 기록한다!  
```java
readBytes(ByteBuf dest);
```
해당하는 ByteBuf를 읽고 해당 길이만큼 readerIndex를 증가한다  
만일 읽으려고 하는 Byte가 고갈되면?  
`IndexOutOfBoundsException`이 발생한다

```java
ByteBuf buffer = ...;
while (buffer.isReadable()) {
    System.out.println(buffer.readByte());
} // 모든 buffer를 읽는 예제
```

쓰기 작업은 읽기와 똑같지만, 쓰는 역할이므로 설명은 생략한다 😄  

2개의 인덱스를 초기화 하고 싶다면?  
> clear() 를 호출하자  

## ByteBufHolder

데이터 페이로드에 다양한 속성 값을 저장해야 하는 경우가 있는데요.!  
이럴때 편리한 인터페이스를 제공하고자 만든 고급 기능들이 있습니다  

|이름|설명|
|---|---|
|content()|ByteBufHolder에 저장된 ByteBuf를 반환|
|copy()|ByteBuf 데이터의 공유되지 않는 복사본이 들어있는 ByteBufHolder의 완전한 복사본을 반환|
|duplicate()|ByteBuf 데이터의 공유된 복사본이 들어있는 ByteBufHolder의 간소한 복사본을 반환|

## ByteBufUtil

그 외에 `ByteBuf` 를 조작하기 위한 기능들은 여기 있으니 참고바란다!  
