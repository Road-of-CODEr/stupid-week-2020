## Netty in action

### Chapter 1

- 자바 네트워킹 API - OIO/NIO 간단한 특징
- 네티의 간단한 소개
    - Channel
    - 콜백
    - Future
    - 이벤트와 핸들러

### Chapter 2

- 네티 기반의 간단한 클라이언트와 서버를 만드는 예제
    - [EchoServerHandler](./netty/EchoServerHandler.java)
    - [EchoServer](./netty/EchoServer.java)
    - [EchoClientHandler](./netty/EchoClientHandler.java)
    - [EchoClient](./netty/EchoClient.java)


### Chapter 3

- `Channel`, `EventLoop`, `ChannelFuture` 개념과 아키텍쳐
- `ChannelHandler`, `ChaanelPipeline` 개념과 아키텍쳐
- `BootStrap` , `ServerBootStrap` 개념과 아키텍쳐

### Chapter 4

- OIO/NIO 네트워킹 애플리케이션 개발에서 네티를 사용함으로써 얻는 개발 편의성
- 전송 API - `Channel` 
- 네티가 지원하는 전송들의 간단한 아키텍쳐
    - NIO, Epoll, OIO, 로컬, 임베디드

