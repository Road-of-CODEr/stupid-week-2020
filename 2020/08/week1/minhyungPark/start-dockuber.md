### 2.2.6 도커 볼륨

도커 이미지로 컨테이너를 생성하면 이미지는 읽기 전용이 되며 컨테이너의 변경 사항만 별도로 저장해서 각 컨테이너의 정보를 보존합니다. 이미 생성된 이미지는 어떠한 경우로도 변경되지 않으며, 컨테이너 계층에 원래 이미지에서 변경된 파일시스템 등을 저장합니다.

따라서, 컨테이너를 삭제하면 컨테이너 계층에 저장돼 있던 정보도 삭제된다. 도커의 컨테이너는 생성과 삭제가 매우 쉬우므로 실수로 컨테이너를 삭제하면 데이터를 복구할 수 없다.

이를 방지하기 위한 방법들이 있다.

#### 2.2.6.1 호스트 볼륨 공유

```shell
$ docker run -d \
--name wordpressdb_hostvolume \
-e MYSQL_ROOT_PASSWORD=password \
-e MYSQL_DATABASE=wordpress \
-v /home/wordpress_db:/var/lib/mysql \
mysql:5.7
```

```shell
$ docker run -d \
--name wordpress_hostvolume \
-e WORDPRESS_DB_PASSWORD=password \
--link wordpressdb_hostvolume:mysql \
-p 80 \
wordpress
```

-v 옵션

- 호스트의 `/home/wordpress_db` 디렉터리와 컨테이너의 `/var/lib/mysql` 디렉터리를 공유한다
- 미리  `/home/wordpress_db`  디렉터리를 호스트에 생성하지 않았어도 도커는 자동으로 이를 생성
- 호스트와 컨테이너의 디렉터리는 동기화되는 것이 아니라 완전히 같은 디렉터리
- 정확히 말하면, -v 옵션을 통한 호스트 볼륨 공유는 호스트의 디렉터리를 컨테이너의 디렉터리에 마운트

#### 2.2.6.2 볼륨 컨테이너

-v 옵션으로 볼륨을 사용하는 컨테이너를 다른 컨테이너와 공유

```shell
$ docker run -i -t \
--name volumes_from_container \
--volumes-from volume_overide \
ubuntu:14.04
```

- -v 또는 --volume 옵션을 적용한 컨테이너의 볼륨 디렉터리를 공유

#### 2.2.6.3 도커 볼륨

`docker volume` 명령어를 사용

도커 자체에서 제공하는 볼륨 기능을 활용해 데이터를 보존

```shell
$ docker volume create --name myvolume
```

호스트와 볼륨을 공유할 때 사용한 -v 옵션의 입력과는 다르게 다음과 같은 형식으로 입력

- [볼륨의 이름]:[컨테이너의 공유 디렉터리]

```shell
$ docker run -i -t --name myvolume_1 \
-v myvolume:/root/ \
ubuntu:14.04
```

- 볼륨은 디렉터리 하나에 상응하는 단위로서 도커 엔진에서 관리
- 도커 볼륨도 호스트 볼륨 공유와 마찬가지로 호스트에 저장함으로써 데이터를 보존하지만 파일이 실제로 어디에 저장되는지 사용자는 알 필요가 없다





