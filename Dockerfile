#
# Build stage
#
FROM maven:3.8.2-jdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:11-jdk-slim
COPY --from=build /target/visualcash-0.0.1-SNAPSHOT.jar visualcash.jar
# ENV PORT=5432
EXPOSE 5432
ENTRYPOINT ["java","-jar","visualcash.jar"]