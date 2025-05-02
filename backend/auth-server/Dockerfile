# Dockerfile for Auth Server
FROM eclipse-temurin:21-jdk-alpine as runtime

WORKDIR /app

# Copiamos el JAR compilado
COPY target/auth-server-*.jar app.jar

# Ejecutamos el servicio
ENTRYPOINT ["java", "-jar", "app.jar"]
