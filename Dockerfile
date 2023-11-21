FROM ubuntu:latest as builder
RUN apt-get update -y && apt-get upgrade -y
RUN apt install openjdk-17-jdk openjdk-17-jre maven -y
WORKDIR /app
COPY . .
RUN mvn package

FROM tomcat:9.0.83-jdk17-temurin-focal
COPY --from=builder /app/target/tomcatsample.war /usr/local/tomcat/webapps/tomcatsample.war
EXPOSE 8080
CMD ["catalina.sh", "run"]