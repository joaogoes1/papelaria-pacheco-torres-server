FROM openjdk:21-jdk-slim
CMD ["./gradlew", "clean", "bootJar"]
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
EXPOSE 8080