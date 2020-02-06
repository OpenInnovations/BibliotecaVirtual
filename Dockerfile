FROM openjdk:8u242-jre-slim

MAINTAINER Martín Alexis Samán Arata

EXPOSE 8000

COPY target/securityservice-0.0.1.jar securityservice.jar

CMD ["java", "-jar", "securityservice.jar"]

#chmod +x demo.jar
#docker build -t malditoidealismo/securityservice:0.1 .
#docker run -d -p 8000:8000 malditoidealismo/securityservice:0.1