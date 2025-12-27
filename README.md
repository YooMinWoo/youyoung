## 설계 및 구현 시 고민했던 부분

원인: 주문 생성시 동시성 미처리로 인해 수량이 음수로 내려가고, 개수가 0인 상품에 대한 주문이 생성됨

해결: 비관적 Lock 처리를 통해 동시성 처리

결과: 동시성 처리를 통해 개수가 0인 상품에 대한 주문 생성 X

## 데이터베이스 ERD 다이어그램
<img width="812" height="365" alt="image" src="https://github.com/user-attachments/assets/ce120994-b110-45c4-ba00-ce586bd1eae6" />


## 프로젝트 실행 방법
1. 릴리즈 파일로 존재하는 jar 파일 실행
2. 프로젝트 다운로드 받은 뒤 ./gradlew build 이후 생성된 jar 파일 실행

jar 파일 실행 명령어 : java -jar youyoung-0.0.1-SNAPSHOT.jar

## 기타
스웨거 주소 : http://localhost:8080/swagger-ui.html

H2 DB 접속 주소 : http://localhost:8080/h2-console
    
    - username : sa
    
    - password : 없음
