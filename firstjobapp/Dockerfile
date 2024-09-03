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
CMD ["./mvnw", "spring-boot:run"]

