FROM adoptopenjdk/openjdk11:jre-11.0.10_9-alpine
ADD ./*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
