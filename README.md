# Digital Money House Backend

Este repositorio contiene el backend del proyecto **Digital Money House**, estructurado como un proyecto multi-módulo con múltiples microservicios basados en Spring Boot.

## 🚀 Requisitos

- Java 21
- Git

> ⚠️ No es necesario tener Maven instalado globalmente, ya que el proyecto incluye **Maven Wrapper** (`mvnw`).

## 🛠️ Cómo compilar el proyecto

Desde la raíz del repositorio, puedes compilar todos los submódulos usando:

```bash
./mvnw clean install

mvnw.cmd clean install

DMHBackend/
├── backend/
│   ├── accounts-server/
│   ├── activities-server/
│   ├── auth-server/
│   ├── cards-server/
│   ├── config-server/
│   ├── eureka-server/
│   ├── gateway/
│   └── users-server/
├── .mvn/
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md

