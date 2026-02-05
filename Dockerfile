
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/wallet-service-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
