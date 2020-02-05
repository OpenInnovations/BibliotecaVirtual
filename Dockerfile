FROM ofayau/ejre

MAINTAINER José Quispe

RUN java -version

EXPOSE 8081

COPY searchservice-1.jar demo.jar

CMD ["java", "-jar", "demo.jar"]

#chmod +x demo.jar
#docker build -t jquispeluyo/searchservice:0.1 .
#docker run -d -p 8081:8081 jquispeluyo/searchservice:0.1