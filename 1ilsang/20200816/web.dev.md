Web dev 세션 보기

Day 1,2,3 welcome 은 생략.

[Day 3: Chrome web.dev LIVE 2020](https://www.youtube.com/watch?v=F1kYBnY6mwg&list=PLNYkxOF6rcIDJHOcBzho38p6WTn3vESvQ&index=5)

- 페이지의 50%는 이미지로 되어있다.
- 많은 사람들이 이미지 최적화를 하지 않는다.

## 이미지 코덱에 따른 유형들

- 손실(lossy): JPEG, WebP
- 무손실(lossless): PNG, GIF, Also WebP
- Vector: SVG

> 위의 설명은 압축의 손실형 무손실형 벡터형 에 따른 확장자다.

사이즈가 커지는 요인
- Lossy: 날카로운 모서리, 세부 사항 없는 영역의 디테일
- Lossless: 1. 빈번하거나 예측할 수 없거나 많은 수의 컬러가 변경될 때 2. 많은 수의 컬러들
- Vector: 도형의 숫자, 도형의 복잡도 + 도형의 복잡도로 인한 CPU 비용이 발생! 벡터에서만 일어난다.

WebP vs JPEG
- WebP 가 더 좋다.
- WebP 는 알파 투명도를 지원함.
- 근데 사파리에서 WebP를 지원하지 않으므로 JPEG 가 좋을 수 있다.

이미지 압축 유형에 따른 적절한 전략으로 이미지를 최대한 줄이자.