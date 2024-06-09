FROM openjdk:21-jdk
EXPOSE 9999
ARG JAR_FILE=*.jar
WORKDIR /app
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]

