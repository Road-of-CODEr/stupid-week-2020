# Web dev 세션 보기

[Day 3: What’s new in Web Payments](https://www.youtube.com/watch?v=ZXmKKV7R72c&list=PLNYkxOF6rcIDJHOcBzho38p6WTn3vESvQ&index=14&t=0s)

- Docs: https://web.dev/payments/
- 결제 모듈을 웹에서 쉽게 사용할 수 있도록 브라우저 차원에서 제공.
- 기본 스펙은 W3C 의 표준을 따른다.
- iOS 에서는 지원하고 있지 않다!

## 웹 기반의 결제 앱의 장점

- 판매자 웹 사이트에서 모달로 결제가 이루어져 리다이랙트 혹은 팝업을 사용하는 일반 앱결제 보다 더 나은 사용자 경험을 제공
- Web Payments API 를 기존 웹 사이트에 "통합"하여  사용자의 정보를 계속 사용할 수 있다.(배송지 등 귀찮게 안함)
- 기존 앱과 달리 웹 기반 결제는 "설치할 필요가 없다"

## 웹 기반 결제의 작동 방식

- "서비스 워커"를 포함하여야 한다. 서비스 워커가 아래의 역할을 한다.
  - 모달 창을 열고 결제 앱의 인터페이스를 표시
  - 결제 앱과 판매자 간의 커뮤니케이션을 연결
  - 고객으로부터 승인을 받으면 결제 자격 증명을 판매자에게 전달

`WebAuthn`, `Credential Management API`, `WebOTP` 를 통해 인증과 결제의 신뢰성을 향상시킨다.

> WebOTP 는 아직 개발중.

이 기능이 어떻게 더 활성화 될지 매우 궁금하넹