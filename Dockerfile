FROM openjdk:17-alpine


LABEL maintainer="your-email@example.com"


WORKDIR /app


COPY target/article-handler-0.0.1-SNAPSHOT.jar /app/spring-boot-app.jar


EXPOSE 8080


ENTRYPOINT ["java", "-jar", "/app/spring-boot-app.jar"]