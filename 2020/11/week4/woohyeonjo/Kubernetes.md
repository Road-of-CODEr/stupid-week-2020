# 왕초보도 따라하는 도커 기초

> 8 ~ 15 강



### 도커 소개

- 컨테이너 기술을 지원하는 다양한 프로젝트 중 하나 그러나 **사실상 표준**
- 애플리케이션에 국한되지 않고 의존성 및 파일 시스템까지 패키징하여 빌드, 배포, 실행을 단순화
- 리눅스의 네임 스페이스와 cgroups와 같은 커널 기능을 사용하여 **가상화**



도커는 다양한 클라우드 서비스 모델과 같이 사용 가능

- 이미지: 필요한 프로그램과 라이브러리, 소스를 설치한 뒤 만든 하나의 파일
- 컨테이너: 이미지를 격리하여 독립된 공간에서 실행한 가상 환경
- Iaas, Paas, Saas



### 도커 설치하기

- VM으로 Ubuntu 설치
- 관리자 권한으로 넘어가기 `sudo -i`
- `apt install docker.io`
  - 자원 사용 불가 -> `reboot`
- 설치확인 (도커 도움말) `docker`
- 이미지 검색 `docker search [imagename]`
  - 실습에서는 tomcat 설치
- 이미지 설치 및 실행 `docker run -d -p 8080:8080 --name tc consol/tomcat-7.0`



### 내가 원하는 이미지 찾기 도커 레지스트리

- 레지스트리: 도커 이미지의 마켓같은 곳
- https://hub.docker.com 을 이용하면 손쉽게 원하는 이미지를 찾을 수 있다.
  - 경로가 앞에 있으면 일반 이용자가 올린 것
  - 없는 것은 오피셜
- 로그인하면 `private`도 가능
- 이미지만 다운로드 받고싶을 때는 `docker pull [imagename]`
- 다운로드한 목록 `docker images`



### 도커 라이프 사이클 이해하기

- Registry <-> Image
  - 이미지 업로드: PUSH
  - 이미지 다운로드: PULL
- Image <-> Container
  - 이미지 실행을 위해 컨테이너 생성 CREATE
  - 파일을 이미지로 만들기 COMMIT
- Conatainer <-> Memory
  - 메모리에 컨테이너 올려서 실행 START
  - 컨테이너 메모리에서 내림 STOP
- 컨테이너 삭제 RM
- 이미지 삭제 RMI



### 도커 라이프 사이클 명령어 실습

- 실행중인 컨테이너 확인 `sudo docker ps`
  - 모든 컨테이너 확인 `sudo docker ps -a`
- 컨테이너 중지/삭제
  - 중지 `sudo docker stop [container name]`
  - 삭제는 중지된 상태에서만 가능 `sudo docker rm [container name]`
- 이미지 삭제 `docker rmi -f [image name]`



### 이미지 비밀 레이어

- 이미지는 여러 개의 레이어로 이루어지며, 같은 레이어는 공유한다.
- 도커 이미지 정보 확인
  - `docker inspect [image name]`
- 도커 이미지 저장소 위치 확인
  - `docker info`
- 디렉토리 크기 확인
  - `du -sh [directory name]`
- image 디렉토리에는 간단 정보만 등록되고, 이미지의 실질적인 정보는 overlay2에 들어있다.



### 도커의 유용한 명령어

-  포트포워딩으로 톰캣 실행하기
  - `docker run -d --name tc -p 80:8080 tomcat`
  - -d : 백그라운드 실행
  - --name tc : 이름 설정
- 컨테이너 내부 쉘 실행
  - `docker exec -it tc / bin/bash`
- 컨테이너 로그 확인
  - `docker logs tc`
- 호스트 및 컨테이너 간 파일 복사
  - `docker cp [path] [to container]:[path]`
  - `docker cp [from container]:[path] [path]`
  - `docker cp [from container]:[path] [to container]:[path]`
- 임시 컨테이너 생성
  - `docker run -d -p 80:8080 --rm --name tc tomcat`
- 전체 컨테이너 정지
  - 전체 컨테이너 아이디만 출력 `docker ps -a -q`
  - `docker stop [백틱]docker ps -a -q[백틱]`
  - 백틱 안에 명령어를 입력하면 그대로 출력



### 도커 컨테이너 실행 연습문제

- 기존에 설치된 모든 컨테이너와 이미지 정지 및 삭제

  ```bash
  sudo docker stop `sudo docker ps -a -q`
  sudo docker rm `sudo docker ps -a -q`
  sudo docker rmi `sudo docker images -q`
  ```

- 도커 기능을 사용해 jenkins 검색

  ```bash
  sudo docker search jenkins
  ```

- jenkins를 사용하여 설치

  ```bash
  sudo docker pull jenkins
  sudo docker inspect jenkins
  sudo docker run -d -p 8080:8080 --name jk jenkins
  ```

- jenkins 포트로 접속하여 웹 서비스 열기

  ```bash
  firefox 127.0.0.1:8080
  브라우저에 캐시가 남아있는 경우에는 ctrl + shift + del
  ```

- jenkins의 초기 패스워드 찾아서 로그인하기

  ```bash
  sudo docker exec -it jk cat /path/initPassword
  sudo docker logs jk
  ```

  