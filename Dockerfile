FROM maven:3.6.3-jdk-8

EXPOSE 8080

COPY ./ ./

RUN mvn clean package

ENTRYPOINT ["java","-jar","target/bank-0.0.1-SNAPSHOT.jar"]