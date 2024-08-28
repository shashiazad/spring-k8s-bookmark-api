# Use the official OpenJDK image as a base image
FROM eclipse-temurin:17-jdk-focal

ADD target/*.jar /app.jar

RUN sh -c 'touch /app.jar'

# Expose the port the app runs on
EXPOSE 8080

# Define the command to run the app
CMD ["sh", "-c", "java -jar /app.jar"]
