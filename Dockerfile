# Build Stage
FROM maven:3.8.7-openjdk-18 AS build

WORKDIR /build

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests

# Runtime Stage
FROM amazoncorretto:17

WORKDIR /app
COPY --from=build /build/target/Sandwichi-*.jar /app/

EXPOSE 8080

ENV DB_URL=jdbc:postgresql://db:5432/ingredient
ENV ACTIVE_PROFILE=dev

CMD ["java", "-jar", "-Dspring.profiles.active=${ACTIVE_PROFILE}", "-Dspring.datasource.url=${DB_URL}", "Sandwichi-0.0.1-SNAPSHOT.jar"]
