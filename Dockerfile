FROM adoptopenjdk/openjdk11:jre-11.0.10_9-alpine

ADD ./*.jar /tmp/
RUN mv /tmp/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
