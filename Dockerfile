FROM openjdk:11-jre-slim
ADD build/libs/route-mesh-0.0.1-SNAPSHOT.jar route-mesh-docker.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "route-mesh-docker.jar"]
