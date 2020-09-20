## 5. HOC(Higher order component)

```javascript
function(value) {
	return addFour(
		function(value) {
			return multiplyThree(
				function(value) {
					return multiplyTwo(
						(k => k)(value)
					);
				}(value)
			);
		}(value)
	);
}
```

- 위의 코드는 ((x _ 2) _ 3) + 4 의 조합이다.
- 함수가 함수를 부르면서 재활용성이 극대화됨.

### 다중 커링으로 HOC 반환하기.

```javascript
export default function (loadingMessage = "Loading...") {
  return function withLoading(WrappedComponent) {
    const { displayName, name: componentName } = WrappedComponent;
    const wrappedComponentName = displayName || componentName;

    function WithLoading({ isLoading, ...otherProps }) {
      if (isLoading) return loadingMessage;
      return <WrappedComponent {...props} />;
    }

    WithLoading.displayName = `withLoading(${wrappedComponentName})`;
    return WithLoading;
  };
}
```

- 커링에 대한 이해 필요

## 6. 컨텍스트로 데이터 관리하기

```javascript
import React from "react";
const { Provider, Consumer } = React.createContext({});

export { Consumer };

export default class LoadingProvider extends React.PureComponent {
  constructor(props) {
    super(props);

    this.state = {};
    this.setLoading = this.setLoading.bind(this);
  }

  setLoading(key, value) {
    const newState = { [key]: value };
    this.setState(newState);
  }

  render() {
    const context = {
      ...this.state,
      setLoading: this.setLoading,
    };

    return <Provider value={context}>{this.props.children}</Provider>;
  }
}
```

- `createContext()` 함수에 빈 객체를 인자로 전달해 공급자와 소비자 생성.
- `export Consumer` 를 통해 이후 `Provider` 가 컨텍스트 데이터를 구독하게 됨.
- value 를 프로퍼티에 전달
- 자식 프로퍼티를 출력해 자식 컴포넌트에 컨텍스트 데이터 전달.
