# Dockerfile for Users Server
FROM eclipse-temurin:17-jdk-alpine as runtime

# Metadata
LABEL maintainer="Digital Money House"
LABEL service="users-server"

# Crear directorio de trabajo
WORKDIR /app

# Copiar el JAR generado por Maven
COPY target/users-server-*.jar app.jar

# Exponer el puerto por defecto (ajusta según configuración real)
EXPOSE 8080

# Ejecutar el servicio
ENTRYPOINT ["java", "-jar", "app.jar"]
