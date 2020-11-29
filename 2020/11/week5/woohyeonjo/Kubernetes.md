# 왕초보도 따라하는 도커 기초

> 16강 ~ 21강



### 환경 변수 사용해 MySQL 서비스 구축하기

```cmd
``// nginx 설치하며 환경변수 설정
docker run -d --name nx -e env name=test1234 --rm nginx

docker exec -it nx bash

// 환경변수 확인
printenv
printenv env_name
echo $env_name

// MYSQL 설치하며 환경변수 설정
docker run --name ms -e MYSQL_ROOT_PASSWORD='!qhdkscjfwj@' -d mysql

// MYSQL 접속
docker exec -it ms mysql -u root -p
```



### 볼륨 마운트하여 Jupyter LAB 서비스 구축

- 볼륨 마운트 옵션 사용해 로컬 파일 공유하기

  `docker run -v <호스트 경로>:<컨테이너 내 경로>:<권한>`

  - 권한의 종류
    - ro: 읽기 전용
    - rw: 읽기 및 쓰기

```cmd
// 볼륨 마운트
docker run -d -p 80:80 --rm -v /var/www:/usr/share/nginx/html:ro nginx

// 볼륨 마운트를 하면 공유할 컨텐츠를 호스트 경로에 둘 수 있다.
cd /var/www/
echo test1234 > index.html

// jupyter notebook 사용하기
mkdir jupy ernotebook
cd jupyternotebook
docker run --rm -p 8080:8888 -e JUPYTER_ENABLE_LAB=yes -v "$PWD":/home/jovyan/work:rw jupyter/datascience-notebook:9b06df75e445
(마지막은 hash값)
```



### 직접 도커 이미지 빌드하기

```cmd
// 파이썬 에코 서버 만들기
gedit test_server.py
python3 test_sserver.py

// 다른 창에서
nc 127.0.0.1 12345

// 이미지 빌드하기
mkdir my_first_project
mv test_server.py ./my_first_project/
cd my_first_projcet
gedit dockerfile

//dockerfile
FROM python:3.7

RUN mkdir /echo
COPY test_server.py /echo

CMD ["python", "/echo/test_server.py"]

// 빌드하기
docker build -t echo_test .
docker images

// test
docker run -t -p 12345:12345 --name et --rm echo_test
```



### 도커 이미지 푸시와 히스토리 확인

```cmd
docker login

docker tag echo_test:latest <id>/echo_test:<version>
docker images

docker push <id>/echo_test

docker history <image fullname>
```



### 프라이빗 레지스트리 서버 구현 및 사용

 ```cmd
docker run -d --name docker-registry -p 5000:5000 registry

docker tag <image fullname> <ip>/<image name>

docker push <image new name>
 ```



### 풀스택 워드프레스 컨테이너 이미지 만들기

- 공식 워드프레스 이미지는 DB가 없는데 합쳐서 이미지를 만들어보자!

```cmd
docker run --name WP -p 80:80 -d tomsik68/xampp

wget <워드프레스 url>
tar -xf latest-ko_KR.tar.gz

sudo docker exec -it WP bash
chown daemon. /opt/lampp/htdocs
cd /opt/lampp/htdocs/
mkdir backup
mv * ./backup/
exit

docker cp wordpress WP:/opt/lampp/htdocs
sudo docker exec -it WP bash
cd opt/lampp/htdocs/
cd wordpress
mv * ../

// image 생성 및 push
sudo docker stop WP
docker commit WP <id>/wordpress
docker login
docker push <image fullname>
```



