FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
EXPOSE 8080
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
ADD src/devicemanagement-0.0.1-SNAPSHOT.jar /app/devicemanagement.jar
ENTRYPOINT ["java", "-jar", "/app/devicemanagement.jar"]