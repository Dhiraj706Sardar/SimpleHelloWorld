# Spring Boot DevOps Demo

A simple Spring Boot application demonstrating CI/CD with GitHub Actions and Docker.

## Features

- Spring Boot 3.2.0
- Java 17
- Docker containerization
- GitHub Actions CI/CD pipeline
- Maven build system

## Prerequisites

- Java 17 or later
- Maven 3.6.3 or later
- Docker (for local development)
- Git

## Local Development

### Build the Application

```bash
mvn clean package
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will be available at `http://localhost:8080`

### API Endpoints

- `GET /` - Welcome message
- `GET /health` - Health check endpoint

## Docker

### Build Docker Image

```bash
docker build -t dhiraj143/helloworld .
```

### Run Docker Container

```bash
docker run -p 8080:8080 dhiraj143/helloworld
```

## CI/CD Pipeline

The project includes a GitHub Actions workflow that:

1. Builds and tests the application on every push
2. Builds and pushes a Docker image to Docker Hub on the main branch

### Required Secrets

Add these secrets to your GitHub repository:

- `DOCKER_HUB_USERNAME` - Your Docker Hub username
- `DOCKER_HUB_TOKEN` - Your Docker Hub access token

## Project Structure

```
.
├── .github/workflows/  # GitHub Actions workflows
├── src/                # Source code
│   ├── main/java/      # Java source files
│   └── main/resources/ # Configuration files
├── .gitignore          # Git ignore file
├── Dockerfile          # Docker configuration
├── mvnw                # Maven wrapper (Unix)
├── mvnw.cmd           # Maven wrapper (Windows)
└── pom.xml             # Maven configuration
```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
