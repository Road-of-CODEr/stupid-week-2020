## Netty in action

### Chapter 5
> ByteBuf

- `ByteBuf` API의 장점
    - 사용자 정의 버퍼 형식으로 확장할 수 있음
    - 내장 복합 버퍼 형식을 통해 투명한 제로 카피를 달성할 수 있음
    - 용량을 필요에 따라 확장할 수 있음 - (`StringBuilder` 처럼)
    - `ByteBuffer` 의 `filp()` 메서드 호출 없이도 reader 와 writer 모드를 전환할 수 있음
    - 읽기와 쓰기에 고유 인덱스를 적용함 - `readerIndex`, `writerIndex`
    - 메서드 체인, 참조 카운팅, 풀링이 지원됨
- `get()`, `set()` 작업은 지정한 인덱스에서 시작하며 인덱스를 변경하지 않는다
- `read()`, `write()` 작업은 지정한 인덱스에서 시작하며 접근한 바이트 수만큼 인덱스를 증가시킨다


### Chapter 6
> ChannelHandler와 ChannelPipeline

- Channel의 수명주기
    - ChannelUnregistered : Channel 생성 , EventLoop에 등록되지 x
    - ChannelRegistered : EventLoop에 등록
    - ChannelActive : Channel이 활성화됨
    - ChannelInactive : Channel이 원격 피어로 연결되지 않음
- ChannelHandler
    - ChannelInboundHandler
    - ChannelOutboundHandler
- ChannelPipeline
    - Channel을 통해 오가는 인바운다와 아웃바운드 이벤트를 가로채는 ChannelHandler의 인스턴스 체인
    - 새로운 Channel 마다 새로운 ChannelPipline이 할당
- ChannelHandlerContext
    - ChannelHandler와 ChannelPipeline 간의 연결을 나타내며 ChannelHandler를 ChannelPipeline에 추가할 때마다 생성
    - 연결된 ChannelHandler와 동일한 ChannelPipeline 내의 다른 ChannelHandler 간의 상호작용을 관리


### Chapter 7
> EventLoop와 스레딩 모델

- 네티 4의 모든 입출력 작업과 이벤트는 EventLoop에 할당된 스레드에 의해 처리된다
- 지연 실행 또는 주기적으로 실행해야하는 작업을 등록할수 있다
