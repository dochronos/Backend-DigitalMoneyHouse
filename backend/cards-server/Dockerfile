# Use a lightweight OpenJDK image
FROM openjdk:17-jdk-slim

# Metadata
LABEL maintainer="Digital Money House Team"
LABEL description="Docker image for Cards microservice"

# Define working directory
WORKDIR /app

# Argument for the JAR file name
ARG JAR_FILE=target/cards-server-0.0.1-SNAPSHOT.jar

# Copy the jar file into the image
COPY ${JAR_FILE} app.jar

# Expose port (opcional si el microservicio tiene un puerto específico)
# EXPOSE 8082

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
