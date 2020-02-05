FROM ofayau/ejre

MAINTAINER Martín Alexis Samán Arata

RUN java -version

EXPOSE 3001

COPY fileprocessinglite-0.0.1.jar fileprocessinglite-0.0.1.jar

CMD ["java", "-jar", "fileprocessinglite-0.0.1.jar"]

#chmod +x demo.jar
#docker build -t malditoidealismo/fileprocessinglite:0.1 .
#docker run -d -p 3001:3001 malditoidealismo/fileprocessinglite:0.1