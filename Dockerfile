FROM maven:onbuild AS buildenv

FROM openjdk:jre-alpine
COPY --from=buildenv /usr/src/app/target/searchservice-1.jar /searchservice.jar
EXPOSE 8081
CMD ["java", "-jar", "/searchservice.jar"]