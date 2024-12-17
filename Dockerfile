# Usa una imagen base de OpenJDK 20
FROM openjdk:17-jdk-slim

WORKDIR /app

RUN apt-get update && apt-get install -y curl nano iputils-ping

COPY . .

RUN chmod +x gradlew


RUN ./gradlew clean build -x test


EXPOSE 8083

CMD ["java", "-jar", "build/libs/ProyectoFinalAnneDecor-0.0.1-SNAPSHOT.jar"]
