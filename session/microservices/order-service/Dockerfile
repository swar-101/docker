# Stage 1: Build the application using Maven and Java 17
FROM maven:3.9.2-eclipse-temurin-17 AS builder
WORKDIR /app
# Copy pom.xml and source code
COPY pom.xml .
COPY src ./src
# Package the application without running tests
RUN mvn clean package -DskipTests

# Stage 2: Run the application using a lightweight JRE
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Copy the packaged jar from the builder stage (adjust the jar name if needed)
COPY --from=builder /app/target/order-service-0.0.1-SNAPSHOT.jar app.jar
# Expose the port that matches your application.properties (here 8082)
EXPOSE 8082
# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
