## 기능

- 회원 등록
- 회원 조회
- 회원 목록 조회
- 회원 수정

## 프로젝트 스펙
- Spring boot 3.1.5 LTS
- JDK 21 LTS
- JPA
- H2
- JUnit5 & Mockito
- lombok
- logback
- QueryDsl
- OpenAPI Specification (Swagger)
## 아키텍쳐
- 헥사고날
  - port & adapter
  - in & out 
- 멀티 모듈
  - api
  - application
  - domain
  - infra-jpa

## 실행 방법 1

### **build**

- 터미널 실행 후 프로젝트 상단으로 이동한 다음 아래와 같이 빌드 명령어를 실행한다

```json
./gradlew :api:build
```

### **jar 실행**

- java 21이 설치되어 있어야합니다.
- 아래와 같이 api/build/libs에 위치한 `hexagonal-sample-api.jar`를 실행

```json
java "-Dfile.encoding=UTF-8" -jar api/build/libs/hexagonal-sample-api.jar --spring.profiles.active=local
```

## 실행 방법 2
```json
cd api
docker build -t hexagonal-sample-app .
docker run -p 8080:8080 -e "SPRING_PROFILES_ACTIVE=local" hexagonal-sample-app
```