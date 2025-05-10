# Stock-Trader
제로베이스 과제 프로젝트 [주식매매프로그램]

## 🧾 설계
### ✔ 요구사항 명세서
[🔗 기능 및 비기능 요구사항 명세서](https://thoracic-catamaran-22f.notion.site/16e435d0a24b8054a9ede84dd0325fe1?pvs=4)

### ✔ 마일스톤

### ✔ API 명세
스웨거 작업 후 작성

### ✔ 배포다이어그램
[🔗 배포 다이어그램](https://thoracic-catamaran-22f.notion.site/18c435d0a24b80f6a0e0d391b2c14fb8?pvs=4)

### ✔ 시퀀스 다이어그램
#### 주문 및 체결
[🔗 주문 및 체결 시퀀스 다이어그램](https://thoracic-catamaran-22f.notion.site/17f435d0a24b8072ae4ee98b69eb3e69?pvs=4)

### ✔ ERD 문서

### MockAPI

## 🧾 실행

### 🗃️ 로컬 MySQL 컨테이너 실행 방법

프로젝트 루트에 있는 `docker-compose.yml`을 통해 MySQL만 단독 실행할 수 있습니다.
#### MySQL 컨테이너 실행
```bash
docker-compose up -d mysql
```
-d: 백그라운드 모드
•	mysql: docker-compose.yml 안의 서비스 이름 (services.mysql)
### ddl 문 실행
root 디렉토리의 `DDL_STOCK_ITEM_CREATE.sql`을 통해 테이블을 생성합니다.


### 📦 stockTrader Docker Image

Spring Boot 기반 주식 거래 백엔드 서버를 Docker로 패키징한 실행 이미지입니다.  
Gradle과 멀티 스테이지 빌드를 활용하여 빌드 환경과 실행 환경을 분리하고,  
최종 이미지는 `.jar` 파일만 포함한 경량화된 JDK 환경에서 실행됩니다.

---

#### 🐳 Docker 빌드

```bash
VERSION=$(./gradlew -q printVersion)
docker build -t stock-trader:$VERSION .
````
기본적으로 ./build.gradle과 ./src 디렉토리를 기준으로 빌드됩니다.
최종 이미지에는 stockTrader.jar만 포함됩니다.


#### 🚀 Docker 실행
```
docker run \
  -e SPRING_PROFILES_ACTIVE=local \
  -e JAVA_OPTS="-Dloglevel=DEBUG" \
  -p 8080:8080 \
  stock-trader-app
```