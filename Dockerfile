FROM adoptopenjdk/openjdk11:alpine as builder
ADD . /tmp/
RUN cd /tmp/ && ls && \
    rm -rf ./target && \
    rm -rf ./src/main/resources/config/application.properties && \
    ./mvnw package -Dmaven.test.skip=true && \
    mv target/*.jar target/app.jar



FROM adoptopenjdk/openjdk11:jre-11.0.10_9-alpine

COPY --from=builder /tmp/target/app.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
