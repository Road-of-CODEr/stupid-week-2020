## NUXT

### 디렉토리

- Assets

  - Style, Sass, Font, Image 등의 컴파일 되지 않은 에셋 들

- Components

  - 재사용성 Vue 컴포넌트를 포함하는 디렉토리. `asyncData`, `fetch` 메소드를 여기서 사용할 수 없음!

- Layout

  - 골격을 다루는 디렉토리. 사이드바 같은 레이아웃 자체를 박음.(Layout 디렉토리는 추가 설정 없이 변경 불가능)

- Middleware

  - 페이지나 레이아웃이 렌더링 되기 전에 실행할 사용자 정의 함수를 작성

- Pages

  - view 및 라우터를 포함하는 디렉토리.(Layout 과 같이 변경 불가)

- Plugins

  - 루트 vue 앱이 생성되기 전 실행하고 싶은 JS 플러그인을 포함하는 디렉토리. `Global components`, `Functions`, `Constants` 를 `Injection` 할 수 있는 곳.

- Static

  - 서버의 루트에 바로 연결됨.(`favicon`, `robots.txt` 등) 이 또한 변경 불가능

- Store

  - Vuex 파일을 포함하는 디렉토리. 기본적으로 비활성화. `index.js` 파일을 생성하면 프레임워크가 자동으로 Store 를 활성화 한다. 변경불가능.

- nuxt.config.js
  - Nuxt.js 의 사용자 정의 설정을 포함하는 파일.

host, port 변경 방법

env

```json
"scripts": {
"dev": "HOST=0.0.0.0 PORT=3333 nuxt"
}
```

`package.json` nuxt use

```json
"config": {
"nuxt": {
  "host": "0.0.0.0",
  "port": "3333"
}
},
"scripts": {
"dev": "nuxt"
}
```
