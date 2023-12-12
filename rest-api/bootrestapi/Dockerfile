FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar book.jar
ENTRYPOINT ["java","-jar","/book.jar"]
EXPOSE 8080 