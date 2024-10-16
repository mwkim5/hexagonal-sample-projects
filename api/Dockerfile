FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY build/libs/hexagonal-sample-api.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "app.jar"]