# LMS-Spring



Learning Management System

Spring-boot + Vue reimplementation of OOP-Project https://github.com/UAws/OOP-Project

front / back-end separated rest web application



## Dependency

| Scope             | Dependency            |
| ----------------- | --------------------- |
| Base              | Spring Boot 2.4.2     |
| Persistence       | Spring Data JPA       |
| Web               | Spring MVC            |
| Model readability | Lombok                |
| Test              | Junit 5               |
| Security          | Spring Security + Jwt |
| Api explorer      | swagger 2 - 3.0.0     |



## Build project

1. create **application.properties** in **resources/config** folder to define DB properties

```properties
DB_HOST=server.example.com
DB_PORT=3306
DB_NAME=LMS
DB_USER=lms
DB_PWD=123456
```

2. 

```shell
mvn spring-boot:run
```



##  Api Documentation

http://localhost:8080/swagger-ui/#/
