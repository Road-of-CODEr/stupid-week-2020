## Git 기본 명령어 공부

#### 사용자 설정

    git config --global user.name “juheekimm”

    git config --global user.email “yylcd9999@naver.com”

#### 저장소 복제하기

    git clone <저장소 url>

#### 새로운 원격 저장소 추가 

    git remote add <원격 저장소, origin/upstream..> <저장소 url>

#### Index에 Stage

    git add <파일명>

> git add . 을 할 경우 Untracked Files 모두를 한번에 stage 하게 됨

#### 변경사항 확정

    git commit -m <커밋 메세지>

#### Server에서 최신 코드를 받아와 merge 하기

    git pull

#### Git Server에서 최신 코드들 받아오기

    git fetch

#### 브랜치 관리

##### 생성

    git branch <branch_name>

##### 원격 브랜치 목록보기

    git branch -r

##### 로컬 브랜치 목록보기

    git branch -a

#### 합치기

##### 다른 브랜치를 현재 브랜치로 합치기

    git merge <브랜치>

##### 커밋하지 않고 합치기

    git merge --no-commit <브랜치>

##### 선택하여 합치기

    git cherry-pick <커밋명>

##### 커밋하지 않고 선택하여 합치기

    git cherry-pick -n <커밋명>

##### 브랜치의 이력을 다른 브랜치에 합치기

    git merge --squash <브랜치>