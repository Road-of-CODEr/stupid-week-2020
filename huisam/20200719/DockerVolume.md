## Docker Volume

도커에서 data를 안전하게 종속시킬 방법은 3가지 방식이 존재. 

* Volume: 특정 파일 시스템 내에 path로 저장
* bind: Data가 Host System의 어디에든지 저장가능
* tmpfs: Host의 메모리에 저장

### Volume

Container를 관리하는 해당 Host의 File System의 일부에 data를 저장. 

Linux의 경우 `/var/lib/docker/volumes/`

당연한 말이겠지만, docker가 아닌 프로세스가 위의 File System을 건드리면 치명적이겠죠.?  

현재까지 Docker에서 data를 존속시킬 수 있는 최고의 방법. 

### Bind

Host의 System에서 어디에든지 저장가능한 저장 방식. 

저장되는 Data는 **System File** 일 수도 있고, 디렉토리 일 수도 있다. 

Host System의 Memory에서만 저장이 가능하고,  

**절대로 Host의 File System** 에서는 저장 도리 수 없다. 

### 실습

한번 생성해보기  

```shell
docker volume create [volume 이름]
```

그러면 실제로 생성되는 것을 볼 수 있다. 

하나 App을 만들어보자. 

```python
from flask import Flask
app = Flask(__name__)

@app.route('/')
def index():
  return 'Hello Flask'


@app.route('/info')
def info():
  return 'Info'


if __name__ == '__main__':
  app.run(port=4357, host='0.0.0.0')
```

Dockerfile도 만들고~!  

```Do
FROM python:3.8

RUN pip install flask

COPY . "/app/server"

WORKDIR "/app/server"

ENTRYPOINT ["python", "server.py"]
```

이미지를 만들고, 한번 실행시켜볼까?  

```shell
docker run -p 4357:4357 \
--name my_server \
--mount source=myVolume,target=/app/server \
-d my_server
```

이렇게 하면, myVolume에 해당하는 Volume을 해당 container의 /app/server로 Mount 할 수 있다~!  

한번 확인해보고 싶다면?  

```shell
docker inspect my_server | jq '.[].Mounts'
```

그러면 아래와 같이 나온다. 

```json
[
  {
    "Type": "volume",
    "Name": "myVolume",
    "Source": "/var/lib/docker/volumes/myVolume/_data",
    "Destination": "/app/server",
    "Driver": "local",
    "Mode": "z",
    "RW": true,
    "Propagation": ""
  }
]
```



### 참고

* [Docker Reference run - mount](https://docs.docker.com/engine/reference/commandline/run/#mount-volume--v---read-only)