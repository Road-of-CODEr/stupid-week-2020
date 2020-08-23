## kubectl

### Deployment

k8s 환경에서 가장 기본적으로 **stateless** 한 Application :apple: 을 배포 :ballot_box: 하는데 기본이 되는.!  

`Deployment` 부터 정리해볼게영~!  

Deployment에는 기본적으로 옵션을 설정하는 것이 가장 중요한데,   

한번 알아보도록 하겠습니다~. 

<div>
  <img src="img/k8s-deployment.png" text-align="center" />
</div>



### Pod Template

* `.spec.template.spec.restartPolicy` : Pod를 재시작 하는 정책으로, 기본은 항상 `Always`
* `.spec.replicas` : 필요한 Pod의 수를 선택하는 선택적 필드며, 기본은 항상 `1`
* `.spec.selector` : Pod를 선택하는 label의 selector로서, `apps/v1` 으로 배포할 경우, `spec.template.metadata.labels` 하고 항상 일치해야 한다
* `.spec.strategy` : Pod를 대체하는(재배포) 전략으로, 기본은 `RollingUpdate`, 선택지로는 `Recreate`  가 존재
  * 자세한 내용은 [참고](https://www.weave.works/blog/kubernetes-deployment-strategies)
  * `RollingUpdate` 시에 선택해야 되는 선택지들이 몇 개 있다
    * `.spec.strategy.rollingUpdate.maxUnavailable` : 업데이트 진행되는 도중에 사용할 수 없는 최대 파드의 수를 지정하는 것
      * 기본값은 `25` 으로, 25%를 의미하며, 75%의 Pod은 항상 가용하도록 설정하는 것이다
    * `.spec.strategy.rollingUpdate.maxSurge` : 업데이트 진행되는 도중에 최대로 생성할 수 있는 파드의 수를 의마한다
      * 기본값은 `25` 으로, 25%를 의미하며, 기존 및 새 Pod의 수를 의도한 갯수의 `125%` 를 넘지 않도록 한다
* `spec.progressDeadlineSeconds` : deployment를 진행되다가 실패했을 때, 실패 상황을 보고해야 하는데, 실패했으니 대기시간에 대한 설정이 필요하다. 그래서 설정해주는 것. 기본은 항상 `600` 이며 600초를 의미한다.

* `spec.volumes` : Pod에 제공할 볼륨을 설정하고,
* `spec.containers[*].volumeMounts` : 컨테이너에 마운트 할 위치를 지정
  * `name` : volume의 이름 지정
  * `type` : volume에 해당하는 type을 지정해야 하는데, 자세한 것은 [링크](https://kubernetes.io/ko/docs/concepts/storage/volumes/#hostpath) 참고
  * `path` : volume에 해당하는 path 지정



### 작성 예시

```yaml
apiVersion: v1
kind: Service
metadata:
  name: python-service
  labels:
    app.python.server: service # service의 레이블
spec:
  type: LoadBalancer
  ports:
  - protocol: TCP
    port: 2222 # service의 port
    targetPort: 8080 # pod의 target port
  selector:
    app.python.server: node # 연결 시킬 pod의 label
---
apiVersion: apps/v1
kind: Deployment
metadata:
  labels:
    app.python.server: deployment
  name: pyhon-server # deployment의 이름
spec:
  replicas: 2 # 개체수
  selector:
    matchLabels:
      app.python.server: node # pod의 label을 선택해서 가져오기
  template:
    metadata:
      labels:
        app.python.server: node # pod의 label을 지정
    spec:
      containers:
        - image: server_flask:0.0.7
          name: python-server
          ports:
            - containerPort: 8080
          volumeMounts:
            - mountPath: /temp
              name: volume-host
      volumes: # 참고할 volume 지정
        - name: volume-host # volume 의 이름 지정
          hostPath:
            path: /Users/huisam/volume # volume으로 할 path
            type: DirectoryOrCreate # type 지정
```



### 참고

* [쿠버네티스 deployment](https://kubernetes.io/ko/docs/concepts/workloads/controllers/deployment/)