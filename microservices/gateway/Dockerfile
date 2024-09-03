## Stage 1: Build the application
#FROM maven:3.8.5-openjdk-17-slim AS build
#WORKDIR /app
#COPY pom.xml ./
#COPY src ./src
#COPY .mvn/ .mvn
#COPY mvnw ./
#RUN ./mvnw clean package -DskipTests
#
## Stage 2: Create the final image
#FROM openjdk:17-jdk-slim
#WORKDIR /app
#COPY --from=build /app/target/*.jar company-service.jar
#COPY wait-for-it.sh /wait-for-it.sh
#RUN chmod +x /wait-for-it.sh
#
#EXPOSE 8082
#CMD ["java", "-jar", "company-service.jar"]

# syntax=docker/dockerfile:1

#which "offical java image"?
FROM openjdk:23-jdk-slim
#working directory: ở trong container này có 1 thư mục app, thay vì chúng ta nhảy vào, cd đến thư mục này, thì chúng ta chạy dòng lệnh nayf lun
WORKDIR /app
#copy from your Host(PC, Laptop) to container
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

#Run this inside the image
RUN ./mvnw dependency:go-offline
COPY src ./src
EXPOSE 8084
CMD ["./mvnw", "spring-boot:run"]

