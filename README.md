# Spring Boot DevOps Demo

A simple Spring Boot application demonstrating CI/CD with GitHub Actions, Docker, and Kubernetes.

## Features

- Spring Boot 3.2.0
- Java 17
- Docker containerization
- GitHub Actions CI/CD pipeline
- Maven build system
- Kubernetes deployment
- Datadog monitoring

## Prerequisites

- Java 17 or later
- Maven 3.6.3 or later
- Docker (for local development)
- Git
- Kubernetes cluster
- Datadog API key
- Bugsnag API key

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

## Kubernetes Deployment

### Prerequisites

1. Kubernetes cluster
2. Docker Hub credentials
3. Datadog API key
4. Bugsnag API key

### Deployment Steps

1. Create Kubernetes secrets for API keys:
```bash
kubectl create secret generic datadog-secrets \
  --from-literal=api-key=<your-datadog-api-key>

kubectl create secret generic bugsnag-secrets \
  --from-literal=api-key=<your-bugsnag-api-key>
```

2. Deploy the application:
```bash
kubectl apply -f kubernetes/deployment.yaml
```

3. Deploy monitoring:
```bash
kubectl apply -f kubernetes/monitoring.yaml
```

## Monitoring Setup

### Datadog Dashboard
1. Create a new dashboard in Datadog
2. Add the following metrics:
   - `app.devops-spring-app.response_time`
   - `app.devops-spring-app.request_count`
   - `app.devops-spring-app.error_count`
   - `jvm.memory.used`
   - `jvm.cpu.time`

### Bugsnag Setup
1. Create a new project in Bugsnag
2. Configure error notifications
3. Set up release tracking

## Health Checks

The application has two types of health checks:

1. Liveness Probe:
   - Path: `/actuator/health/liveness`
   - Initial delay: 30 seconds
   - Period: 10 seconds
   - Timeout: 5 seconds
   - Failure threshold: 3

2. Readiness Probe:
   - Path: `/actuator/health/readiness`
   - Initial delay: 30 seconds
   - Period: 10 seconds
   - Timeout: 5 seconds
   - Failure threshold: 3

## CI/CD Pipeline

GitHub Actions are configured to:
1. Build and test the application
2. Build and push Docker image
3. Deploy to Kubernetes
4. Run performance tests

## Monitoring and Logging

The application is monitored using:
- Datadog for metrics and logs
- Bugsnag for error tracking
- Kubernetes health checks

## Support

For any issues or questions, please open an issue in the GitHub repository.
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
