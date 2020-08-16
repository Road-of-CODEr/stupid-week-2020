## NUXT

- vue 프레임워크.(universal 은 ssr)
- 골격이 갖춰져 있어 바로 개발에 집중할 수 있는 장점.

### 구조

1. Incoming Request(요청 시작)
2. nuxtServerInit(요청의 최초에만 실행되는 store action)
3. middleware 진입
   - 1. `nuxt.config` 타고
   - 2. `layout` 타고
   - 3. `page` 및 컴포넌트로 들어감
4. validate()
   - `page` 검증 및 매칭
5. `asyncData()` & `fetch()`
   - `SERVER_MODE`: `process.server` 알아둘것
6. Render
7. Navigate(`<nuxt-link>`)
   - 네비게이션으로 움직이면 middleware 부터 실행됨.
   - **새로고침 및 URL 직접 접근은 incomig Request 에 해당하므로 처음부터 다시탐**

### asyncData

- `asyncData`는 컴포넌트를 로드하기 전에 항상 호출됨(**페이지 컴포넌트일때에만**) 서버사이드에선 Nuxt 앱 최초 요청시 한번만. 클라이언트 사이드에서는 추가 경로를 탐색하기 전에 호출 할 수 있다.
- 서버모드, 클라이언트모드는 `process.server`, `process.client` 로 확인할 수 있다. 이 둘의 차이를 잘 알아야함.
- 컨텍스트 객체를 첫 번째 인수로 받아 이를 사용할 수 있는데, 상당피 편리하다.

```html
<template>
  <Home />
</template>

<script>
  import Home from "~/components/Home";

  export default {
    async asyncData({ app, store, req, error, route }) {
      try {
        // app, store, error, req, route 등을 가져와서 사용할 수 있음.
      } catch (err) {
        return error({ statusCode: 500, message: err["message"] });
      }
    },
    components: {
      Home,
    },
  };
</script>
```

- `asyncData` 내부에서 `this` 를 통해 컴포넌트에 접근할 수 없음. **이는 이 메소드가 컴포넌트를 초기화 하기 전에 호출되기 때문!!!** 이거 때문에 겪는 오류를 줄이자.
