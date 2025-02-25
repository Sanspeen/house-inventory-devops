# Step 1: Build stage (compiles the JAR inside Docker)
FROM openjdk:17-jdk-slim as build

WORKDIR /app

# Copy Gradle files and source code
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle build.gradle
COPY settings.gradle settings.gradle
COPY src src

# Give execute permission to Gradle wrapper
RUN chmod +x gradlew

# Build the application
RUN ./gradlew clean build --no-daemon

# Step 2: Runtime stage (runs the built JAR)
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]