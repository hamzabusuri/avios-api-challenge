# Parental Control API Service

Parental Control API Service is a REST API hosted on a beanstalk application in AWS and is used to check whether a user is able to watch a movie based on their parental control level preference. The SpringBoot framework was used to create this REST API service as well as cloud resources to host the service. Integration tests were also created using MockMVC which have passed.

As well as hosting the service on AWS, the REST API can be run locally and a zuul gateway was created which allows the client to connect to the REST API end-point.

## Tech Stack

- Java
- Spring Boot
- Spring Cloud (Zuul Proxy Gateway)
- MockMVC
- Mockito
- Swagger UI
- AWS (DynamoDB, Beanstalk, ECR, Elasticsearch, Cloudwatch, IAM)
- Terraform (Used to deploy AWS services)
- Kibana (used to visualise logs)
- CircleCI (CI/CD pipeline)
- Docker
- Maven
- Jackson

## Running locally

```
- Go to terminal
mkdir pcs-app
cd pcs-app
git clone https://github.com/hamzabusuri/avios-api-challenge.git
cd avios-api-challenge

- To run without zuul gateway run:
cd movieservice
./mvnw clean verify
java -jar target/hamza-0.0.1-SNAPSHOT.jar

- To run with zuul gateway, go to project directory and run:
cd spring-zuul-gateway
./mvnw clean verify
java -jar target/spring-zuul-gateway-0.0.1-SNAPSHOT.jar

NOTE: For this, make sure that the movieservice end-point is running too.
NOTE: PORT numbers for both the zuul gateway and rest api service will be different.


```

## Accessing it through Elastic beanstalk

- Development [URL](https://pcs-app-dev.yfwwjakn29.us-east-1.elasticbeanstalk.com)
- Production [URL](https://pcs-app-prod.7sc6pmxnec.us-east-1.elasticbeanstalk.com)


## Usage

If running the REST API service through beanstalk, on either the development or production URL, hit these example URLs to see the REST API service in action. You can also append these at the end of the localhost url once run locally. (append these at the end of the url)

1) /dynamoDb/permission/level/U/movie/1 (200 OK)
2) /dynamoDb/permission/level/U/movie/6 (404 Movie not found)
3) /dynamoDb/permission/level/***/movie/5 (500 System error)

For more information on which end-points can be hit, append /swagger-ui.html at the end of either the localhost url or AWS beanstalk application url.

## License
[MIT](https://choosealicense.com/licenses/mit/)
