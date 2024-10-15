FROM openjdk:17
EXPOSE 7100
ADD build/libs/e-commerce-products-0.0.1-SNAPSHOT.jar e-commerce-products-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/e-commerce-products-0.0.1-SNAPSHOT.jar"]