## ES 별 키워드

```javascript
window.onload = function () {
  debugger;
  var sports = function () {};
};
```

Function 오브젝트 생성 순서, 방법

1. Function 오브젝트 생성

- function sports() {...}

2. Function 오브젝트로 저장

- { sports: {...} }
  sports 는 생성한 Function 오브젝트 이름.
  오브젝트 타입이 FUnction 오브젝트에 프로퍼티가 없는 상태.

(debugger 최초에는 sports 가 undefined 되어 있다.)

3. sports 오브젝트에 prototype 오브젝트 첨부

4. prototype 에 constructor 프로퍼티 첨부

- prototype.constructor 가 sports 오브젝트 전체 참조. 참조이므로 value 인 것이다.
  개발자 도구가 그냥 참조된 값을 보여주는 것일 뿐. 기존에는 주소값을 가지고 있다.

`constructor: value`

"프로퍼티"이다. 오브젝트가 아니다.

5. prototype 에 **proto** 오브젝트 첨부

- ES5 스펙에 **proto** 를 언급(기술) 하지 않음
- ES6 스펙에 기술.
- 엔진이 사용한다는 뉘앙스로 정의.
- ES5 기준으로 보면 표준이 아니다.

6. [빌트인 Object 오브젝트]의 prototype에 연결된 프로퍼티로 Object 인스턴스를 생성

- 생성한 Object 인스턴스를 prototype.**proto** 에 첨부. (상속)

7. sports 오브젝트에 **proto** 오브젝트 첨부

- sports.**proto** 구조가 됨

8. [빌트인 Function 오브젝트]의 prototype에 연결된 프로퍼티로 Function 인스턴스 생성.

- 기존이 Object 를 빌트인 하던 것과의 차이가 여기서 나타난다. Function 으로 선언해주었기 때문.
- 생성한 Function 인스턴스를 sports.**proto**에 첨부

9. sports 에 argumenst 등을 붙여준다.

[[Scope]] 는 Function 오브젝트가 실행되는 렉시컬(정적) 환경이다.
[[Extensible]] 은 프로퍼티 추가 가능 여부 / [[GetOwnProperty]] 오브젝트 소유의 프로퍼티 디스크립터 속성 반환
[[Code]] 함수에 작성한 자바스크립트 코드 설정. 함수가 호출되엇을 때 실행.
