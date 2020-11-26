### Q1. 주소창에 url을 입력하면 일어나는 일

대부분의 웹 서버는 모두 IP 주소를 가지고 있다.

모든 IP주소를 외울 수는 없기 때문에 도메인이 등장했는데, 도메인은 네트워크상에서 컴퓨터를 식별하는 호스트명을 말한다.

즉, 도메인은 IP주소를 알기쉬운 문자열로 매핑한 것이라고 할 수 있다. (여러개의 도메인이 하나의 IP주소로 매핑될 수도 있다.)

이러한 도메인 정보를 담고 있는 서버가 바로 DNS 서버이다.

DNS 서버는 IP주소와 도메인의 매핑정보를 관리하면서, 도메인 혹은 IP 주소를 묻는 요청이 오면 이에 응답한다.

게다가 DNS에도 캐시가 있어서, 자주 요청을 받는 정보는 캐시로 관리를 한다.

즉, 당신이 브라우저로 웹사이트에 접속할 때 일어나는 일은 요약하면,
**"브라우저에 도메인을 입력하면 → DNS서버에 IP주소를 요청 → 수신한 IP주소에 해당하는 웹서버에 접속"**
하는 것이다.

#### 요청 전송
브라우저에 도메인을 입력하면 DNS에 의해 IP 주소를 얻을 수 있고, 그 IP 주소에 우리가 원하는 웹사이트에 대한 요청을 전송한다.

이 요청에는 URL에 포함된 경로, 그리고 요청 방법 등 여러가지 정보들이 담겨있다.

이러한 정보들은 HTTP(Hypertext Transfer Protocol)로 전송이 된다.

모든 과정과 형식이 HTTP 통신으로 규격화와 표준화가 되어있기 때문에 세부 내용에 대한 부분은 크게 신경쓸 필요가 없다.

서버는 요청을 받아 적절하게 처리하고 다시 응답을 보내온다. 이때 응답은 요청과 비슷하게 동작하며 마찬가지로 HTTP 통신을 사용한다.

#### 응답 파싱
브라우저가 서버로부터 응답을 받아도 처음에는 아무 것도 보이지 않는다. 응답 패킷에 대한 파싱을 먼저 수행해야하기 때문이다.

이때, HTTP 통신으로 서버가 요청 패킷을 어떻게 받는지 신경을 쓰지 않아도 되는 것처럼 우리는 응답 패킷을 어떻게 받을지 신경 쓰지 않아도 된다.

먼저 브라우저가 응답 패킷에 포함된 데이터와 메타데이터를 확인한다. 만약 PDF 파일을 요청했다면 브라우저에는 해당 PDF가 열릴 것이다. 이렇게, 브라우저는 받은 데이터나 파일에 따라 가장 적절한 동작을 수행한다.

그렇다면 웹사이트에 대한 응답은 어떻게 처리될까?

웹사이트는 text/html 타입으로 받는다. 이 타입은 브라우저가 응답 패킷에 포함된 HTML 코드를 파싱하여 화면에 출력하도록 한다.

브라우저는 HTML 코드를 어떻게 파싱하고 출력해야 하는지 알고있기 때문에 웹사이트 요청에 대한 응답도 쉽게 동작할 수 있게 된다. 우리는 어떻게 동작하는지 몰라도 되는것이다.

#### 페이지 출력
브라우저는 서버가 보낸 응답 패킷에서 HTML 코드를 파싱하여 화면에 출력한다.

HTML은 페이지의 구조를 나타내기 위한 마크업 언어이다. 즉, 스타일과 같은 정보는 포함하고 있지 않다. 스타일은 CSS에 의해 이루어지는데 HTML을 먼저 요청하여 받은 후에, HTML에 포함된 데이터에 따라 서버에 추가적으로 요청을 보낼 수 있다.

이와 비슷하게 동적 동작을 위해 자바스크립트도 요청하여 활용한다.

### Q2. http와 https의 차이
> HTTPS: Hypertext Transfer Protocol Secure

두 프로토콜 사이에 가장 커다란 차이점은 바로 SSL 인증서이다. HTTPS는 쉽게 말해서 HTTP 프로토콜에 보안 기능을 추가한 것.

SSL 인증서는 사용자가 사이트에 제공하는 정보를 암호화하는데, 쉽게 말해서 데이터를 암호로 바꾸는 것이다. 이렇게 전송된 데이터는 중간에서 누군가 훔쳐 낸다고 하더라도 데이터가 암호화되어있기 때문에 해독할 수 없다.

그 외에도 HTTPS는 TLS(전송 계층 보안) 프로토콜을 통해서도 보안을 유지한다. TSL은 데이터 무결성을 제공하기 때문에 데이터가 전송 중에 수정되거나 손상되는 것을 방지하고, 사용자가 자신이 의도하는 웹사이트와 통신하고 있음을 입증하는 인증 기능도 제공하고 있다.

> 무결성은 관계형 데이터베이스에서 데이터의 정확성과 일관성을 유지하고, 데이터에 결손과 부정합이 없음을 보증하는 것을 의미한다.

HTTPS가 좋은 점은 보안 문제 뿐 아니라

1. 구글이 HTTPS 웹사이트에 검색 시 가산점을 주기로 함 : 검색엔진 최적화(SEO)

2. 가속화된 모바일 페이지(AMP, Accelerated Mobile Pages)를 만들고 싶을 때도 HTTPS 프로토콜을 사용해야 한다. AMP란 모바일 기기에서 훨씬 빠르게 콘텐츠를 로딩 하기 위한 방법으로 구글이 만든 것인데, AMP는 HTML에서 불필요한 부분을 없앤 것이라고 볼 수 있다.

### Q3. 로드밸런싱이란

로드밸런서는 서버에 가해지는 부하(=로드)를 분산(=밸런싱)해주는 장치 또는 기술을 통칭하는 것.

클라이언트와 서버풀(Server Pool, 분산 네트워크를 구성하는 서버들의 그룹) 사이에 위치하며 한 대의 서버로 부하가 집중되지 않도록 트래픽을 관리해 각각의 서버가 최적의 퍼포먼스를 보일 수 있도록 한다.

그렇다면 로드밸런싱은 모든 경우에 항상 필요할까?

로드밸런싱은 여러 대의 서버를 두고 서비스를 제공하는 분산 처리 시스템(Scale-out)에서 필요한 기술이다.

서비스의 제공 초기 단계라면 적은 수의 클라이언트로 인해 서버 한 대로 요청에 응답하는 것이 가능하다. 하지만 사업의 규모가 확장되고, 클라이언트의 수가 늘어나게 되면 기존 서버만으로는 정상적인 서비스가 불가능해진다. 이처럼 증가한 트래픽에 대처할 수 있는 방법은 크게 두 가지이다.

#### Scale-up

- 서버 자체의 성능을 확장하는 것을 의미. 비유하자면 CPU가 i3인 컴퓨터를 i7으로 업그레이드하는 것과 같다.

- 스케일 업의 경우 모든 부하가 서버 한 대에 집중되므로 장애시 위험하다.

- 모든 데이터를 한 곳에서 처리하므로 데이터 갱신이 빈번하게 일어나는 데이터베이스 서버에 적합하다.

#### Scale-out

- 기존 서버와 동일하거나 낮은 성능의 서버를 두 대 이상 증설하여 운영하는 것을 의미한다. CPU가 i3인 컴퓨터를 여러 대 추가 구입해 운영하는 것에 비유할 수 있다.

- Scale-out의 방식으로 서버를 증설하기로 결정했다면 여러 대의 서버로 트래픽을 균등하게 분산해주는 로드밸런싱이 반드시 필요하다.

- 다중화를 통해서 서버에서 장애가 나더라도 다른 서버에서 대응이 가능

- 반면 모든 서버가 동일한 데이터를 가지고 있어야 하기 데이터 변화가 적은 웹서버에 적합

#### 알고리즘 종류

- 라운드로빈 방식(Round Robin Method)

    서버에 들어온 요청을 순서대로 돌아가며 배정하는 방식. 어미새가 아기새들 순서대로 돌아가면서 먹이 주는것 같은.

    클라이언트의 요청을 순서대로 분배하기 때문에 여러 대의 서버가 동일한 스펙을 갖고 있고, 서버와의 연결(세션)이 오래 지속되지 않는 경우에 활용하기 적합하다.

- 가중 라운드로빈 방식(Weighted Round Robin Method)

    각각의 서버마다 가중치를 매기고 가중치가 높은 서버에 클라이언트 요청을 우선적으로 배분한다. 주로 서버의 트래픽 처리 능력이 상이한 경우 사용되는 부하 분산 방식

    예를 들어 A라는 서버가 5라는 가중치를 갖고 B라는 서버가 2라는 가중치를 갖는다면, 로드밸런서는 라운드로빈 방식으로 A 서버에 5개 B 서버에 2개의 요청을 전달한다.

- IP 해시 방식(IP Hash Method)

    클라이언트의 IP 주소를 특정 서버로 매핑하여 요청을 처리하는 방식.

    사용자의 IP를 해싱해(Hashing, 임의의 길이를 지닌 데이터를 고정된 길이의 데이터로 매핑) 로드를 분배하기 때문에 사용자가 항상 동일한 서버로 연결되는 것을 보장한다. 

- 최소 연결 방식(Least Connection Method)

    요청이 들어온 시점에 가장 적은 연결상태를 보이는 서버에 우선적으로 트래픽을 배분한다. 자주 세션이 길어지거나, 서버에 분배된 트래픽들이 일정하지 않은 경우에 적합하다.

- 최소 리스폰타임(Least Response Time Method)

    서버의 현재 연결 상태와 응답시간(Response Time, 서버에 요청을 보내고 최초 응답을 받을 때까지 소요되는 시간)을 모두 고려하여 트래픽을 배분한다. 가장 적은 연결 상태와 가장 짧은 응답시간을 보이는 서버에 우선적으로 로드를 배분하는 방식이다.

### pom.xml과 web.xml

pom,xml : 빌드/배포와 관련된 모든 설정을 담고 있고 maven 이라는 유틸리티에서 메타 정보로 사용하는 설정파일.
     
web.xml : 웹과 관련된 설정 (리스너, 어플리케이션 파라미터, 서블릿 설정, 필터 설정 등..)을 담고 있고 tomcat은 web.xml 이지만 웹 컨테이너 마다 조금씩 이름은 다르다.
     
### XML과 Json의 차이점

#### XML이란?

XML은 EXtensible Markup Language의 약자로 XML은 HTML과 매우 비슷한 문자 기반의 마크업 언어(text-based markup language)이다. 이 언어는 사람과 기계가 동시에 읽기 편한 구조로 되어 있다.
  
XML은 HTML처럼 데이터를 보여주는 목적이 아닌, 데이터를 저장하고 전달할 목적으로만 만들어졌다. 또한, XML 태그는 HTML 태그처럼 미리 정의되어 있지 않고, 사용자가 직접 정의할 수 있다.
 
#### JSON과 XML의 공통점

JSON과 XML은 다음과 같은 공통점을 가지고 있다.

1. 둘 다 데이터를 저장하고 전달하기 위해 고안되었다.

2. 둘 다 기계뿐만 아니라 사람도 쉽게 읽을 수 있다.

3. 둘 다 계층적인 데이터 구조를 가다.

4. 둘 다 다양한 프로그래밍 언어에 의해 파싱될 수 있다.

5. 둘 다 XMLHttpRequest 객체를 이용하여 서버로부터 데이터를 전송받을 수 있다.
  
#### JSON과 XML의 차이점

하지만 JSON과 XML은 다음과 같은 차이점도 가지고 있다.

1. JSON은 종료 태그를 사용하지 않다.

2. JSON의 구문이 XML의 구문보다 더 짧다.

3. JSON 데이터가 XML 데이터보다 더 빨리 읽고 쓸 수 있다.

4. XML은 배열을 사용할 수 없지만, JSON은 배열을 사용할 수 있다.

5. XML은 XML 파서로 파싱되며, JSON은 자바스크립트 표준 함수인 eval() 함수로 파싱다.
 
#### JSON의 사용 범위

XML 문서는 XML DOM(Document Object Model)을 이용하여 해당 문서에 접근한다. 하지만 JSON은 문자열을 전송받은 후에 해당 문자열을 바로 파싱하므로, XML보다 더욱 빠른 처리 속도를 보여준다. 따라서 HTML과 자바스크립트가 연동되어 빠른 응답이 필요한 웹 환경에서 많이 사용되고 있다.
  
하지만 JSON은 전송받은 데이터의 무결성을 사용자가 직접 검증해야 한다. 따라서 데이터의 검증이 필요한 곳에서는 스키마를 사용하여 데이터의 무결성을 검증할 수 있는 XML이 아직도 많이 사용되고 있다.