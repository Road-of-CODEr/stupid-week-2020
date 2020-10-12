## 웹 브라우저가 메시지를 만든다.

### STORY 01. HTTP 리퀘스트 메시지를 작성한다.
#### 1. 탐험 여행은 URL 입력부터 시작한다

**URL(Uniform Resource Locator)**

<img width="990" alt="그림-1 1" src="https://user-images.githubusercontent.com/46305139/95684546-6e635f80-0c2d-11eb-8eb0-45cbd012dc04.png">

![그림1-2](https://user-images.githubusercontent.com/46305139/95684558-7f13d580-0c2d-11eb-9e72-efb608d08e7d.jpg)

> 그림 1-1 : 각종 URL의 형식

위에서 보다시피 다양한 URL 형식이 준비되어 있는 것은 이유가 있다. 브라우저는 웹 서버에 액세스하는 클라이언트로 사용하는 경우가 많지만, 브라우저의 기능은 그 외의 기능도 제공합니다. 예를 들어 다운로드/업로드하는 `FTP`의 기능, 메일의 클라이언트 기능도 가지고 있다.

모든 URL 에서는 하나의 공통점이 있다. URL 맨 앞의 `http:` , `ftp:` , `file:`, `mailto:` 에서 액세스하는 방법을 나타내는데 이것들을 `프로토콜` 이라고 부릅니다.



####  2. 브라우저는 먼저 URL을 해독한다.

브라우저는 가장 처음해야하는 일은 입력된 URL을 해독하는 것이다. URL 형식은 프로토콜에 따라 다르므로 여기에서는 `http` 기준으로 설명합니다.

![그림1-3](https://user-images.githubusercontent.com/46305139/95684570-9226a580-0c2d-11eb-979b-b538e2350a19.jpg)

URL은 먼저 위와 같은 요소로 분해합니다.

예를 들어 `http://www.lab.cyber.co.kr/dir1/file1.html`  이라는 URL을 입력시,

-  `http:` : 프로토콜
- `//` 
- `www.lab.cyber.co.kr`  : 웹 서버명
- `/dir1/file1.html` : 해당 웹서버의 파일의 경로

이와 같이 분해할 수 있습니다.

즉, 웹 서버의 파일을 찾아 그 해당 파일에 액세스한다는 의미입니다.



#### 3. 파일명을 생략한 경우

구체적은 파일명을 생략한 URL을 본 경험이 있을 것이다. 크게 보면 4가지 경우가 있는데 이 때에는 어떻게 리소스를 선택하는지 살펴보자.

**case 1. `http://www.lab.cyber.co.kr/dir/`**

URL 규칙에서는 이와 같이 파일명을 생략해도 괜찮지만, 어느 파일에 액세스해야 할지 모릅니다. 

따라서 이럴 경우를 대비해 서버측에서 파일명을 미리 설정해둔다.`index.html` 또는 `default.html` 이라는 파일명을 주로 설정해둔다. 따라서 위와 같이 파일명을 생략하면, 서버에 따라 `/dir/index.html` or `/dir/default.html` 이라는 파일에 액세스한다.



**case 2. `http://www.lab.cyber.co.kr/`**

끝에 `/` 가 있으므로 디렉토리가 지정되고 파일명만 생략된 경우.

따라서 `/index.html` 또는 `/default.html` 이라는 파일에 액세스



**case 3. `http://www.lab.cyber.co.kr`**

끝에 `/` 까지 생략된 경우. 이와 같이 디렉토리명까지 생략해 버리면 무엇을 요청하고 있는지 알지 못하게 되므로 너무 지나친 생략이지만 이런 방법도 인정된다. 경로명이 아무 것도 없는 경우에는 루트 디렉토리 아래에 있는 미리 설정된 파일명의 파일, 즉 `case 2` 와 같게 됩니다.



**case 4. `http://www.lab.cyber.co.kr/whatisthis`**

끝에 `/` 가 없으므로 `whatisthis`를 파일명으로 보는게 맞을 거 같지만, 실제로 사용자 입장에서 파일명을 생략하는 규칙을 정확히 이해하지 못하고 디렉토리 끝의 / 까지 생략하는 경우가 있다. 

따라서, 이런 경우에는 다음과 같이 취급하는 것이 통례다.

- 웹서버에 `whatisthis` 라는 파일이 있으면, 파일명으로
- `whatisthis` 디렉토리가 있으면, 디렉토리명으로

> 동일한 파일명과 디렉토리가 동시에 존재할 수 없으므로, 둘 다 존재하는 경우는 없다.



#### 4. HTTP의 기본 개념

브라우저는 URL을 해독하면 어디에 액세스할지 결정되는데 그 후 *HTTP* 프로토콜을 이용하여 웹 서버에 액세스하게 된다. 따라서, **HTTP(HyperText Transfer Protocol)**에 대해 간단히 알아보자.

**HTTP(HyperText Transfer Protocol)**

>  W3 상에서 정보를 주고받을수 있는 프로토콜이다. 주로 HTML 문서를 주고 받는데에 쓰인다. -  [wikipedia]

클라이언트와 서버가 주고받은 요청/응답의 메시지의 내용이나 순서를 정한것이다. 

리퀘스트 메시지에는 `무엇을` , `어떻게 해서 ` 라는 내용이 쓰여있다.

'`무엇을`' : *URI* 라는 것으로 나타낸다.

**URI(Uniform Resource Identifier) - 통합 자원 식별자**

인터넷에 있는 자원을 나타내는 유일한 주소이다. 

![uri](https://user-images.githubusercontent.com/46305139/95684585-ae2a4700-0c2d-11eb-90db-8b852ec8f8cf.png)



`어떻게 해서` : HTTP 메소드

웹 서버에 어떤 동작을 하고 싶은지를 전달

| 메소드  | HTTP 1.0 | HTTP 1.1 | 의미                                                         |
| ------- | -------- | -------- | ------------------------------------------------------------ |
| GET     | O        | O        | URI로 지정한 정보를 도출                                     |
| POST    | O        | O        | 클라이언트에서 서버로 데이터를 송신                          |
| HEAD    | O        | O        | GET과 비슷. 단, HTTP 메시지 헤더만 반송하고 데이터의 내용을 돌려보내지 않습니다. |
| OPTIONS |          | O        | 통신 옵션을 통지하거나 조사할 때 사용합니다                  |
| PUT     | △        | O        | URI로 지정한 서버의 파일을 치환합니다.  - 목적 리소스 모든 현재 표시를 요청 payload로 바꿉니다. |
| DELETE  | △        | O        | 목적 리소스를 삭제합니다.                                    |
| TRACE   |          | O        | 목적 리소스의 경로를 따라 메시지 loop-back 테스트를 합니다.  |
| CONNECT |          | O        | 암호화한 메시지를 프록시로 전송할 때 이용하는 메소드입니다.  |



리퀘스트 메시지가 웹 서버에 도착하면 웹 서버는 그 속에 쓰여있는 내용을 해독합니다. 결과 데이터를 응답 메시지에 저장합니다. 응답 메시지의 맨 앞부분에는 실행결과를 나타내는 Status code (ex. 200 OK, 404 Not Found)가 포함되어 있습니다. 이러한 응답 메시지가 클라이언트측에 도착하면 브라우저가 메시지를 해석하여 화면에 표시해주면서 HTTP 동작은 끝납니다.

리퀘스트 메시지와 응답 메시지의 자세한 내용은 뒤에서 알아보겠다.

#### 5. HTTP 리퀘스트 메시지를 만든다.

브라우저는 URL을 해독하고 웹서버와 파일명을 판단하면 이것을 바탕으로 HTTP 메시지를 작성합니다.

```bash
# 리퀘스트 메시지 포맷
<메소드><공백><URI><공백><HTTP 버전> # 이 첫번째 행을 '리퀘스트 라인'
<필드명>:<필드값>                  # '메시지 헤더 부분'
...
...
...
<공백 행>
<메시지 본문>      # 메시지 본문 : 클라이언트에서 서버에 송신하는 데이터

# 응답 메시지 포맷
<HTTP 버전><공백><스테이터스 코드><공백><응답 문구>
<필드명>:<필드값>
...
...
...
<공백 행>
<메시지 본문>  	# 메시지 본문 : 서버에서 클라이언트에 송신하는 데이터
```

HTTP 요청과 응답의 구조는 서로 닮았으며, 그 구조는 다음과 같다.

1. 시작 줄(start-line)에는 실행되어야 할 요청, 또은 요청 수행에 대한 성공 또는 실패가 기록되어 있습니다. 이 줄은 항상 한 줄로 끝납니다.
2. 옵션으로 HTTP 헤더 세트가 들어갑니다. 여기에는 요청에 대한 설명, 혹은 메시지 본문에 대한 설명이 들어갑니다.
3. 요청에 대한 모든 메타 정보가 전송되었음을 알리는 빈 줄(blank line)이 삽입됩니다.
4. 요청과 관련된 내용(HTML 폼 콘텐츠 등)이 옵션으로 들어가거나, 응답과 관련된 문서(document)가 들어갑니다. 본문의 존재 유무 및 크기는 첫 줄과 HTTP 헤더에 명시됩니다.

![그림1-4](https://user-images.githubusercontent.com/46305139/95684572-9488ff80-0c2d-11eb-9fcf-3c77bf6164f9.png)

> 그림 출처 - [developer.mozilla.org](https://developer.mozilla.org/ko/docs/Web/HTTP/Messages)



리퀘스트 메시지는 크게 3가지 요소로 구성되어 있다.

1. **리퀘스트 라인 - 시작줄**
2. **헤더**
3. **본문**



**리퀘스트 라인**

리퀘스트 라인은 *3가지 요소*로 이루어져 있다.

1. **메소드** : 브라우저의 동작 상태에 따라 이 메소드가 결정된다.

   *브라우저의 동작 상태 예시*

   - 브라우저의 URL 입력창에 URL을 입력
   - 웹 페이지 안의 하이퍼링크 클릭
   - 폼에 데이터를 기입하여 submit
   - ...

2.  **URI**

   URI 부분에는 파일이나 프로그램의 경로명을 쓰는 것이 보통입니다.

3. **HTTP 버전**



**헤더(Header)**

헤더는 부가적인 자세한 정보를 제공하는 역할을 한다. 다양한 종류의 요청 헤더가 있는데, 이들은 몇몇 그룹으로 나누어진다.

- General 헤더 : 리퀘스트 메시지와 리스폰스 메시지 둘 다 사용되는 헤더
  - ex) `Connection`, `Via`, `Cache-Control`
- Request 헤더 : 리퀘스트 메시지에 사용되는 헤더
  - ex) `Accept`, `Authorization` , `User-Agent`
- Entity 헤더 : 요청 본문에 적용되는 헤더. 요청 본문이 없는 경우 entity 헤더는 전송되지 않는다.
  - ex) `Content-Length`



**본문**

메시지 헤더 뒤 공백 행을 넣고 그 뒤에 이어지는 부분. 메시지의 실제 내용이 된다. 일반적으로 `GET`, `HEAD`, `DELETE`, `OPTIONS` 처럼 리소스를 가져오는 요청은 보통 본문이 필요없다.



#### 6. 리퀘스트 메시지를 보내면 응답이 되돌아온다.

응답에 대한 자세한 내용은 6장에서 다루기 때문에, 간단하게 알아보자.

리스폰스 메시지의 포맷도 리퀘스트 메시지와 비슷하다. 
다만 첫번째 행의 경우, 리퀘스트의 실행 결과를 나타내는 Status code 와 응답 문구를 포함하고 있다.

| code | description                        |
| ---- | ---------------------------------- |
| 1xx  | 처리의 경과 상황 등을 통지         |
| 2xx  | 정상 종료                          |
| 3xx  | 무언가 다른 조치가 필요함을 나타냄 |
| 4xx  | 클라이언트측의 오류                |
| 5xx  | 서버측의 오류                      |

**한 개의 리퀘스트에 대해 한개의 응답만 돌려 보낸다.**

따라서, 한 페이지내에 여러 컨텐츠(이미지 또는 영상)을 포함한 경우 각각 요청을 보내야 한다.

![그림1-5](https://user-images.githubusercontent.com/46305139/95684574-95ba2c80-0c2d-11eb-886c-cf50e12dd5bc.png)

> 그림 출처 - [developer.mozilla.org](https://developer.mozilla.org/ko/docs/Web/HTTP/Overview)

**예시** - html 내의 이미지 태그를 포함한 경우

1. 리퀘스트 메시지

```http
GET /sample.htm HTTP/1.1
Accept: */*
Accept-Language: ja
Accept-Encoding: gzip, deflate
User-Agent: Mozilla/4.0
Host: www.lab.glasscom.com
Connection: Keep-Alive
```

2. '/sample1.htm' 의 리스폰스 메시지

```http
HTTP/1.1 200 OK
Date: Wed, 7 Oct 2020 13:00:00 GMT
Server: Apache
Last-Modified: Wed, 30 Sep 2020 13:00:00 GMT
ETag: "5a9da-27903c726b61"
Accept-Ranges: bytes
Content-Length: 632
Connection: close
Content-Type: text/html

<html>
<head>
<meta http-equiv-"Content-Type" content="text/html"; charset=utf-8">
<title> 인터넷 탐험 여행</title>
</head>

<body>
<img src="gazou.jpg"/>
</body>
</html>
```

3. '/gazou.jpg' 에 해당하는 리퀘스트 메시지

```http
GET /gazou.jpg HTTP/1.1
Accept: */*
Referer: http://www.lab.cyber.co.kr/sample1.htm
Accept-Language: ja
Accept-Encoding: gzip, deflate
User-Agent: Mozilla/4.0
Host: www.lab.cyber.co.kr
Connection: Keep-Alive
```

4. 리스폰스 메시지

```http
HTTP/1.1 200 OK
Date: Wed, 7 Oct 2020 13:00:00 GMT
Server: Apache
Last-Modified: Wed, 30 Sep 2020 13:00:00 GMT
ETag: "5a9da-1913-3aefa236"
Accept-Ranges: bytes
Content-Length: 6419
Connection: close
Content-Type: image/jpeg

이미지 바이너리 데이터
```



