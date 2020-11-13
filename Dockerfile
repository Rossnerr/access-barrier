#For maven
FROM maven:3-jdk-11-slim AS MAVEN_BUILD

# cd /build
WORKDIR /build
COPY pom.xml /build/
COPY src /build/src/
RUN mvn clean install -DskipTests

# For Java 11
FROM openjdk:11-slim
# cd /app
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/accessbarrier-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
# java -jar /app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
