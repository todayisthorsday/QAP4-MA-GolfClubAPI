FROM eclipse-temurin:21-jdk-alpine

# Set work directory
WORKDIR /app

# Copy everything
COPY . .

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose port
EXPOSE 8080

# Run the jar
CMD ["java", "-jar", "target/golfclub-api-0.0.1-SNAPSHOT.jar"]
