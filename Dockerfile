# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:21-jdk AS build

# Set the working directory
WORKDIR /app

# Copy the Maven project descriptor (pom.xml) and install dependencies
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline

# Copy the entire project and build it
COPY . .
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests

# Use a minimal runtime image
FROM eclipse-temurin:21-jre

# Set working directory
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/join_backend-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
