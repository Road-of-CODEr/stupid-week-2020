[프로그래머스 SQL 문제 목록](https://programmers.co.kr/learn/challenges)
##### 오랜 기간 보호한 동물(2)
```sql
SELECT      I.ANIMAL_ID, I.NAME
FROM        ANIMAL_INS I, ANIMAL_OUTS O
WHERE       I.ANIMAL_ID = O.ANIMAL_ID
ORDER BY    DATEDIFF(O.DATETIME, I.DATETIME) DESC
LIMIT       2;
```

##### 루시와 엘라 찾기
```sql
SELECT      ANIMAL_ID, NAME, SEX_UPON_INTAKE
FROM        ANIMAL_INS
WHERE       NAME IN ("Lucy", "Ella", "Pickle", "Rogan", "Sabrina", "Mitty")
ORDER BY    ANIMAL_ID;
 ```

##### 이름에 el이 들어가는 동물 찾기
```sql
SELECT      ANIMAL_ID, NAME
FROM        ANIMAL_INS
WHERE       LOWER(NAME) LIKE "%el%" AND ANIMAL_TYPE = "Dog"
ORDER BY    NAME;
 ```

##### DATETIME에서 DATE로 형 변환
```sql
SELECT      ANIMAL_ID, NAME, LEFT(DATETIME, 10)
FROM        ANIMAL_INS
ORDER BY    ANIMAL_ID;
 ```