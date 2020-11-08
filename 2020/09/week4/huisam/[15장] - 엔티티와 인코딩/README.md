## HTTP 완벽가이드

### :one::five: 장 엔티티와 인코딩

#### 메세지는 컨테이너 :package:, 엔티티는 화물 :truck:

| 헤더             | 설명                                                         |
| ---------------- | ------------------------------------------------------------ |
| Content-Type     | 엔티티에 의해 전달된 객체의 종류                             |
| Content-Length   | 전달되는 메세지의 길이나 크기                                |
| Content-Language | 전달되는 객체와 가장 잘 대응되는 자연어                      |
| Content-Encoding | 객체 데이터에 대해 행해진 변형                               |
| Content-Location | 요청 시점을 기준으로, 객체의 또 다른 위치                    |
| Content-Lange    | 이 엔티티가 부분 엔티티라면, 엔티티가 전체에서 어떤 부분인지 정의 |
| Content-MD5      | 엔티티 본문의 콘텐츠에 대한 체크섬                           |
| Last-Modified    | 서버에서 이 콘텐츠가 생성 혹은 수정된 날                     |
| Cache-Control    | 어떻게 이 문서가 캐시될 수 있는지에 대한 지시자              |
| Expires          | 이 엔티티 데이터가 더 이상 신선하지 않은 것으로 간주되기 시작하는 날짜와 시각 |
| ETag             | 이 인스턴스에 대한 고유 검사기, 엔티티를 위한 헤더           |
| Content-MD5      | 엔티티 본문의 콘텐츠에 대한 체크섬                           |

보통 엔티티의 본문은 헤더 필드의 끝을 의미하는 빈 `CRLF` 줄 바로 다음부터 시작하더라~!  

#### Content-Length :arrow_left:

보통은 해당 엔티티 본문의 크기를 나타내지만, gzip으로 압축되어 있으면 압축된 후의 크기를 의미한다. 

이 `Content-Length` 헤더는 꼭 필수적이다. 지속적인 커넥션이 유지중인 HTTP 라면, 메세지의 끝을 인식해야되기 때문이다. 

이 헤더는 몇 가지 주의할 점들이 있는데..

* `Transfer-Encoding` 헤더와 같이 오는경우에는, 반드시 이 헤더를 무시해야 한다 (전송된 바이트를 바꿀 것이므로)
* `Multipart` 요청의 경우, 각각의 part 마다 Content-Length를 지정해줘야 메세지를 어디까지 읽을지 판단할 수 있다

#### Content-MD5 :key:

서버가 엔티티 본문에 `MD5` 알고리즘을 적용한 결과를 보내기 위해 사용한다. 

만일 gzip으로 압축된 엔티티를 보낸다면, 압축된 결과에 알고리즘을 적용해야 한다~!  

```text
MD5 알고리즘을 통해 하고 싶은 궁극적인 목표는 메세지에 대한 무결성이다 
```

#### Charset :abc: 

엔티티의 타입을 명시하는 `Content-Type` 헤더와 같이 사용되는 헤더다. 

```
Content-Type: text/html; charset=iso-8859-4
```

Multipart 요청의 경우

```text
Content-Type: multipart/form-data; boundary=[hklfhweoifw]
```

boundary를 통해서 여러 메시지의 포멧들을 분리할줄 알아야한다. 

#### Content-Encoding

<div>
  <img src="https://user-images.githubusercontent.com/34855745/98437351-c3a76980-2124-11eb-95ca-99a240505d48.png" text-align="center"/>
</div>


위 그림처럼 Content-Length 헤더가 같이 변화하는 것을 볼 수 있다. 

실제로 Content-Encoding 헤더는 대상의 **Request / Response 를 압축**하여. 

`Network-Bandwidth` 를 줄이는데에 목적이 있다.!  

콘텐츠 인코딩의 종류로는

* Gzip: 엔티티에 GNU zip 인코딩이 되어있음을 의미한다
* Compress: 엔티티에 유닉스 파일 압축프로그램인 `compress` 가 실행되었다
* deflate: 엔티티가 zlib 포맷으로 압축되었다
* Identity: 엔티티에 어떠한 인코딩도 실시하지 않았다
* [Br](https://brotli.org/): 구글이 만든 압축 알고리즘 (내가 알기로는 최고의 성능을 자랑한다. 역시 갓구글 )

Content-Encoding이 엔티티의 본문의 인코딩 헤더라면,  

실제 응답으로 받고 싶은 인코딩 헤더도 미리 지정할 수 있다. 

그게 바로 `Accept-Encoding`. 

#### 전송 인코딩과 청크 인코딩

청크 인코딩은 `Transfer-Encoding: chunked` 라는 헤더로 사용되며,  

위 인코딩 방식은 엔티티만 인코딩 하는 것이 아니라, **메세지 전체를 인코딩한다.** 

```text
청크 인코딩은 메세지를 일정 크기의 청크 단위로 쪼개어 순차적으로 보내는 방식을 의미한다
```

그러니까 지속적인 연결에 대한 HTTP 에서 많이 유리한 방식이다. 

<div>
  <img src="https://user-images.githubusercontent.com/34855745/98437347-c1dda600-2124-11eb-8b18-9b2a15bbd6c7.png" text-align="center" />
</div>




그래서 청크 인코딩은 콘텐츠 인코딩 방식과 병행해서 사용이 가능하다.!

```text
Content-Type: text/html
Content-Encoding: gzip
Transfer-Encoding: chunked
```

먼저 `text/html` -> `gzip` -> `chunked data` 순차적으로 진행하고, 디코딩은 반대로 진행한다. 

또한 이러한 Transfer-Encoding 방식은 `HTTP 1.1` 에서 새로 적용된 방식이니 참고드린다. 

#### 검사기와 신선도 :white_check_mark:

보통 서버에서 제공하는 콘텐츠는 시간에 따라 변하기 마련이다. 

똑같은 리소스라도, 과거에 받은 리소스랑 현재 서버가 유지하는 리소스는 다를 수 있다. 

*이럴 때 어떻게 처리할까?*  

<br>

엔티티에 대한 신선도 검사는.. `Expires` 헤더와 `Cache-Control` 헤더를 통해 진행할 수 있다. 

서버가 클라이언트에게 응답으로 내려주는 콘텐츠가 언제까지 신선하다고 이야기를 해주는 것이다 ㅎㅎ. 

우리는 이러한 과정 전체를 이미 **7장 Cache** 에서 잘 다뤘으니 넘어가도록 하겠다. 

#### Range

엔티티에 대하여 특정 범위를 지정하는 헤더도 있다. 

그것이 바로 `Range` 헤더. 

클라이언트가 아래와 같이 요청을 보냈다면

```text
GET /bigfile.html HTTP/1.1
Host: www.naver.com
Range: bytes=4000-
```

4000 Byte부터 되는 시점 이후의 모든 데이터를 달라는 것이다. 

혹은 큰 파일인지 모르고 클라이언트가 요청했다면..

서버 입장에서 다 내려주는 것은 너무나 힘들기에 `206(Partial Content)` 를 통해서 부분 응답할 수가 있는데,  

이때도 `Range` 헤더를 적용할 수 있다. 

<div>
  <img src="https://user-images.githubusercontent.com/34855745/98437360-c73af080-2124-11eb-8644-191c57456e4c.png" text-align="center" />
</div>




#### ETag

일명 델타 인코딩 방식이라고 하는데, 간단히 말해서는 `버젼` 을 체크하는 방식이라고 생각하면 쉽다. 

<div>
  <img src="https://user-images.githubusercontent.com/34855745/98437358-c4d89680-2124-11eb-8451-51bb5e9875c6.png" text-align="center" />
</div>


순차적으로 설명을 하자면

1. 클라이언트는 엔티티가 없으므로 GET으로 서버에게 요청한다
2. 서버는 해당 시점에서의 엔티티를 `ETag` 와 함께 제공한다
3. 다시 클라이언트가 해당 엔티티에 대한 만료를 인지하고, `If-None-Match` 헤더를 통해 서버에게 받은 ETag 헤더를 전송한다
4. 다시 서버는 새로운 델타를 생성하여 `ETag` 헤더와 함께 클라이언트에게 최신의 엔티티를 제공한다

물론 ETag를 생성하는 과정에서 델타 인코딩에 대한 알고리즘을 적용해야 하는데,  

이 부분이 굉장히 성능에 많이 영향을 끼친다. 

단순히 생각해보자면, `Git` 과 같은 버젼관리 시스템에서 각 Commit 마다 하나의 고유 Hash값을 가지고 있을 텐데,  

각 **Commit의 Hash를 바탕으로 diff 를 하는 연산**이 굉장히 비싸게 들어갈 수 밖에 없다. 

자세한 내용은 책 :book: 을 더 참고하면 좋겠다 

