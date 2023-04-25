FROM amazoncorretto:11

RUN mkdir -p /usr/local/app

COPY build/libs/cache-vista-1.0-SNAPSHOT.jar /usr/local/app/cache-vista.jar

EXPOSE 8080
EXPOSE 9090

CMD ["java", "-jar", "/usr/local/app/cache-vista.jar"]
