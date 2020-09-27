## 계획했던 목표
- 학교에서 알려주지 않는 17가지 실무 개발 기술 완독
- 나중에 다시 찾아볼 키워드 정리

## 3장 날짜와 시간
#### Unix epoch time
- 1970년 1월 1일 0시 0분 0초부터 1초 단위로 증가
- 여러가지 이유들이 있지만 [이게](https://stackoverflow.com/questions/1090869/why-is-1-1-1970-the-epoch-time) 제일 타당해보임
- 36bit 로 표현할 수 있는 시간은 앞 뒤로 68년

#### monotonic time & real time
- 테스트 코드에 new Date 를 쓰면 안되는 이유 

#### time-zone
- 여러 서버가 서로 다른 타임 존을 사용할 때는 문제가 생길 수 있다.

## 4장 정규 표현

## 5장 UUID

## 7장 Hash Function
- 해시충돌
- salt

## 8장 JSON
- JSON의 한계
  - 텍스트 기반의 불필요한 트래픽 오버헤드
  - 클라이언트와 서버의 높은 결합력
- BSON: 몽고 DB의 데이터 처리기술로, 바이너리를 지원하는 JSON 

## 9장 YAML
- anchors, aliases
- anchors 로 지정된 값을 aliases 로 사용 할 수 있다

## 12장 Base64
- 바이너리 데이터를 문자열로 치환
- Base64 vs HTTP multi part


## 14장 HTTP
- CORS
- 비동기 지원 HTTP 2.0, 웹소켓
  - HTTP 2.0 은 하나의 요청에 대한 여러 응답을 병렬로 보내기
  - 웹소켓 웹상에서 상태유지