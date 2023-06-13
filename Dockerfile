FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} preparingCv-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/preparingCv-0.0.1-SNAPSHOT.jar"]