FROM openjdk:8-alpine
WORKDIR /docker/
COPY ./ /
EXPOSE 8080
CMD ["java", "-jar", "alexpit_sbermini.jar"]