## 3. 리액트 컴포넌트

```javascript
import React from 'react';

class MyComponent extends React.Component {
	componentDidupdate() { console.log('MyComponent update') }
}

class MyPureComponent extends React.PureComponent {
	componentDidUpdate() { console.log('MyPureComponent update') }
}

class App extneds React.Component {
	constructor(props) {
		super(props);
		this.listValue = [{name: 'park', name: 'lee'}];
		this.state = { version: 0};
		this.handleClick = this.handleClick.bind(this);
	}

	handleClick() {
		setTimeout(() => {
			this.listValue[0].name = '1ilsang';
			this.setState({version:1});
		})
		setTimout(() => {
			this.listValue = [{name: '1ilsang', {name: 'lee'}}];
			this.setState({version: 2});
		})
	}

	render() {
		return (
			<div className="body">
				<MyComponent value={this.listValue}/>
				<MyPureComponent value={this.listValue}/>
				<button onClick={this.handleClick}>버튼</button>
			</div>
		)
	}
}
```

- PureComponent 와 불변 변수로 성능을 높임.
- 컴포넌트에서 콜백 함수와 이벤트 처리.
- 단방향 흐름 데이터와 콜백 함수 프로퍼티.

## 4. 에어비앤비 디자인 시스템 따라 하기

```javascript
import { configure, setAddon } from "@storybook/react";
import interopRequireDefault from "babel-runtime/helpers/interopRequireDefault";
import JSXAddon from "storybook-addon-jsx";

import "../src/sass/materialize.scss";

function loadStories() {
  const context = require.context("../src/stories", true, /Story\.jsx$/);

  context.keys().forEach((srcFile) => {
    interopRequireDefault(context(srcFile));
  });
}

setAddon(JSXAddon);
configure(loadStories, module);
```

- 스토리북을 통한 UI 테스팅 및 정리
- 스타일 컴포넌트
- 프로퍼티에 따른 컴포넌트 재활용
