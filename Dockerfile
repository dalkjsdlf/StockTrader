# 1. 빌드
# 멀티 스테이지 빌드
# 컨테이너의 파일 최소화(JAR만 남기고 나머지 파일 삭제)
FROM gradle:8.5-jdk17 AS build
WORKDIR /build
COPY . .
RUN gradle bootJar --no-daemon

# 2. 실행
FROM openjdk:17-jdk-slim
WORKDIR /app

# 두 번째 스테이지
# COPY 첫번째 스테이지 두 번째 스테이지
# JAR 복사 (생성된 JAR이 하나라고 가정)
COPY --from=build /build/build/libs/*.jar stockTrader.jar

# ENV로 JVM 옵션 (loglevel, profile 등) 받을 수 있도록 설정
ENV JAVA_OPTS=""
ENV SPRING_PROFILES_ACTIVE=local

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -jar stockTrader.jar"]