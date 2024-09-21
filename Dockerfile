FROM openjdk:17-jdk-alpine
LABEL authors="Miguel Silva"
RUN mkdir /app
WORKDIR /app
COPY target/*.jar /app/neighborfood-v2.jar
EXPOSE 80
ENTRYPOINT ["java", "-jar", "/app/neighborfood-v2.jar"]