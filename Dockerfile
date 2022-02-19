FROM maven:openjdk
MAINTAINER bloomberg.com
CMD ./mvnw clean install
ADD ./target/bloomberg-data-warehouse.war bloomberg-data-warehouse.war
ENTRYPOINT ["java","-jar","/bloomberg-data-warehouse.war"]
EXPOSE 8081
