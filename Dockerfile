# Build stage
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Default command without Datadog
CMD ["java", "-jar", "app.jar"]

# To enable Datadog, use:
# docker run -e DD_AGENT_HOST=... -e DD_ENV=... -e DD_SERVICE=... -v /path/to/dd-java-agent.jar:/app/dd-java-agent.jar your-image java -javaagent:/app/dd-java-agent.jar -jar app.jar
