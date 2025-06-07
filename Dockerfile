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

# Add Datadog agent
ADD https://github.com/DataDog/dd-java-agent/releases/download/v1.17.0/dd-java-agent.jar /app/dd-java-agent.jar

EXPOSE 8080
EXPOSE 8086

ENTRYPOINT ["java", "-javaagent:/app/dd-java-agent.jar", "-jar", "app.jar"]
