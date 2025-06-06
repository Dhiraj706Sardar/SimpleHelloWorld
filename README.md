# DevOps Demo Application

This is a simple Spring Boot application demonstrating CI/CD pipeline setup with GitHub Actions, Docker, and Kubernetes.

## Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher
- Docker
- Kubernetes (for local development, you can use Minikube or Docker Desktop with Kubernetes)

## Local Development

### Build and Run

1. Clone the repository
2. Build the application:
   ```
   mvn clean package
   ```
3. Run the application:
   ```
   java -jar target/*.jar
   ```
4. Access the application at: http://localhost:8080

### Build Docker Image

```bash
docker build -t devops-demo .
```

### Run in Docker

```bash
docker run -p 8080:8080 devops-demo
```

## CI/CD Pipeline

The project includes a GitHub Actions workflow (`.github/workflows/ci-cd.yml`) that:

1. Builds the application with Maven
2. Runs tests
3. Builds a Docker image
4. Tests the container

## Monitoring

For production, you can integrate with:
- Datadog for application performance monitoring
- Bugsnag for error tracking

## Kubernetes Deployment

To deploy to Kubernetes, you can use the following sample deployment file:

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: devops-demo
  labels:
    app: devops-demo
    environment: production
spec:
  replicas: 3
  strategy:
    type: RollingUpdate
    rollingUpdate:
      maxSurge: 1
      maxUnavailable: 0
  selector:
    matchLabels:
      app: devops-demo
  template:
    metadata:
      labels:
        app: devops-demo
        environment: production
      annotations:
        prometheus.io/scrape: "true"
        prometheus.io/port: "8080"
    spec:
      containers:
      - name: devops-demo
        image: your-registry/devops-demo:latest
        imagePullPolicy: IfNotPresent
        ports:
        - containerPort: 8080
          name: http
        resources:
          requests:
            cpu: "100m"
            memory: "256Mi"
          limits:
            cpu: "200m"
            memory: "512Mi"
        livenessProbe:
          httpGet:
            path: /health
            port: 8080
          initialDelaySeconds: 30
          periodSeconds: 10
          timeoutSeconds: 5
        readinessProbe:
          httpGet:
            path: /health
            port: 8080
          initialDelaySeconds: 15
          periodSeconds: 5
          timeoutSeconds: 3
        env:
        - name: SPRING_PROFILES_ACTIVE
          value: "production"
        - name: JAVA_OPTS
          value: "-Xms256m -Xmx512m"

---
apiVersion: v1
kind: Service
metadata:
  name: devops-demo
spec:
  selector:
    app: devops-demo
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
  type: LoadBalancer
```

## License

MIT
