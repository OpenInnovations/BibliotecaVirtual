FROM openjdk:8u242-jre-slim

MAINTAINER José Quispe

EXPOSE 8081

COPY target/searchservice-1.jar searchservice.jar

CMD ["java", "-jar", "searchservice.jar"]

#chmod +x demo.jar
#docker build -t jquispeluyo/searchservice:0.1 .
#docker run -d -p 8081:8081 jquispeluyo/searchservice:0.1