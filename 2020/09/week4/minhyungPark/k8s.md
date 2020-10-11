# Kubernetes
## 지속적인 통합과 전달 설계
> `gulp.js`와 `jenkins`를 이용하여 빌드 파이프라인과 배포를 쿠버네티스 클러스터와 통합하는 방법을 살펴본다.



- [gulp.js 를 이용한 CI/CD](#gulp.js)
- [jenkins](#jenkins)
  - [jenkins plugin을 이용한 CI/CD](#jenkins-plugin)
  - [helm과 minikube](#helm)


###  <a name="gulp.js"></a>gulp.js 를 이용한 CI/CD
- 자바스크립트와 `Node.js`를 사용하여 빌드 프로세스를 자동화하는 간단한 task runner

#### gulp.js 빌드 예제

**install gulp.js**

```bash
$ npm install -g gulp
$ gulp -v
$ npm install --save-dev gulp
$ npm install gulp-git -save
$ npm install --save-dev gulp-shell
```

**how to use**

여기서는 각 3가지 파일을 이용하여 gulp.js 의 예제를 사용해본다.
- `node-gulp-controller.yaml` : node-gulp에 대한 replicationController
- `node-gulp-service.yaml` : node-gulp-controller에 의해 만들어진 pod에 접근할수 있도록 하는 로드밸런서 타입 서비스
- `gulpfile.js` : 모든 빌드 태스크에 대해 정의

**처음 사용시**
```bash
$ cd k8s/
$ gulp clone
$ gulp docker-build
$ gulp create-kube-pod
```

**업데이트시**
```bash
$ gulp pull
$ gulp docker-build
$ gulp update-kube-pod
```



### <a name="jenkins"></a>jenkins

- jenkins를 이용해 쿠버네티스를 사용하는 방법을 알아보자

#### <a name="jenkins-plugin"></a>[jenkins kubernetes plugin](https://github.com/jenkinsci/kubernetes-plugin)
- 젠킨스 플러그인을 사용하여 쿠버네티스 파드에서 젠킨스 슬레이브를 실행할 수 있다.

**요구사항**
- 젠킨스 서버가 필요하다.
```bash
$ docker run --name myjenkins -d -p 8080:8080 -v /var/jenkins_home jenkins
```
**how to use**
minikube 를 기준으로 설명

- kubernetes plugin 설치 : 책 참고
- pfx 파일 만들기

```bash
# pfx 파일 만들기
$ openssl pkcs12 -export -out ~/.minikube/minikube.pfx -inkey ~/.minikube/profiles/minikube/apiserver.key -in ~/.minikube/profiles/minikube/apiserver.crt -certfile ~/.minikube/ca.crt -passout pass:secret
# test
$ curl --cacert ~/.minikube/ca.crt --cert ~/.minikube/minikube.pfx:secret --cert-type P12 https://$(minikube ip):8443
```

- credential 추가 ( `certifacate` 타입 )
  - 만들어진 pfx 파일 업로드 & 비밀번호 입력
- cloud - kubernetes 추가 : 책 참고

#### <a name="helm"></a>helm과 minikube
- helm 을 이용하여 jenkins를 설치하는 방법을 알아보자.

```bash
# namespace 설치
$ kubectl create -f namespace-jenkins.yaml
# persistent volume 
$ kubectl create -f jenkins-volume-yaml
# jenkins 설치
$ helm install --name gsw-k8s-jenkins stable/jenkins --namespace gsw-k8s-jenkins
# nodeport 로 변환
$ kubectl patch svc gsw-k8s-jenkins --type='json' -p '[{"op":"replace","path":"/spec/type","value":"NodePort"}]'
# 비밀번호
$ kubectl get secret --namespace gsw-k8s-jenkins gsw-k8s-jenkins -o jsonpath="{.data.jenkins-admin-password}" | base64 --decode; echo
```
