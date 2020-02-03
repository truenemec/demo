# start db for integration tests
docker-compose -f docker-compose.yml up -d postgres-product-db-test
#build docker image
mvnw clean install dockerfile:build
#run app in docker
docker-compose -f docker-compose.yml up -d
#by default app started at 9000 port
# integration test run
mvnw clean integration-test

REST endpoints
http://localhost:9000/api/v1/category  (secured)
http://localhost:9000/api/v1/product   (unsecured)

credentials:
login=super
password=super

#start app
docker-compose -f docker-compose.yml up -d postgres-product-db
mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.datasource.url=jdbc:postgresql://localhost:6000/postgres"

#swagger
http://localhost:9000/swagger-ui.html#/