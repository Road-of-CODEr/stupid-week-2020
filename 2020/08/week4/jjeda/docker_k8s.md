## Docker

네임 스페이스, cgroups 커널 기능을 사용하여 가상화

도커의 한계
- 서비스가 커지면 관리해야하는 컨테이너 양이 증가


pull -> create -> start // run 한방에
- pull 은 한번하면 더 하지않음
- create는 run 할때마다 실행된다

docker/image 안에는 imagedb는 layerdb 정보를 가지고있고 layerDB 는 overlay2 의 정보를 가지고있다
overlay2 변경사항이 있음
변경사항에 대한 정보는 overlay2/l 에 있음

exec: docker exec -it ${name} /bin/gash // input, terminal

volume moutnt: -v <host>:<container>:<auth> // ro: read only, rw: read & write

dockefile
- RUN 컨테이너 만들 때(이미지 빌드할때)
- CMD 컨테이너가 시작될 때




	
