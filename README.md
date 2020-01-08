
## Build Status Dev

[![CircleCI](https://circleci.com/gh/hamzabusuri/avios-api-challenge/tree/dev.svg?style=svg&circle-token=01a3b9ffa7b03ae35f40424f6b07f54fede58295)](https://circleci.com/gh/hamzabusuri/avios-api-challenge/tree/dev)

# Parental Control API Service

Parental Control API Service is a REST API hosted on a beanstalk application in AWS and is used to check whether a user is able to watch a movie based on their parental control level preference. The SpringBoot framework was used to create this REST API service as well as cloud resources to host the service.


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

## Prerequisities
- AWS Profile must be set within ~/.aws/credentials
- AWS Account with IAM role with access to IAM, EC2, ELB, ECR and other services
- Java 8 (v1.8)
- Terraform
- DockerHub + Docker Account
- CircleCI account

## Contents of repository
- ```.circleci``` - Holds a config.yml file which allows developers to automatically integrate and deploy a docker image to elastic beanstalk once there are any pushes to the codebase.
- ```movieservice``` - Folder containing the implementation of the REST API Movie Service with testing.
- ```spring-zuul-gateway``` - Folder containing the implementation of the spring zuul gateway used to connect to the movie service API.
- ```terraform``` - This folder holds the terraform states, plan and resource creation files for production and development.
- ```clean.sh``` - Bash script for cleaning temporary files.
- ```deploy.sh``` - Bash script for deploying the spring boot application to ELB manually. AWS resources must first be set up.
- ```Dockerfile``` - File holds the configuration to build a docker image of the Spring Boot App Jar.
- ```Dockerrun.aws.json``` - This file tells Beanstalk which image from ECR it should use.
- ```hamza-0.0.1-SNAPSHOT.jar``` - This jar file holds the spring boot application.

## Set up AWS resources using Terraform

1. Access the terraform folder in your terminal. There are two folders; terraformprod and terraformdev.
2. Run ```terraform init```
2. If accessing terraformdev, Run ```terraform plan -out plandev.tfplan```. If accessing terraformprod, Run ```terraform plan -out planprod.tfplan```
  - Fill out name, description & environment as well as profile when prompted.
  - Profile is the name of your profile inside `~/.aws/credentials` file. Default profile is called `default`. You can insert many profiles inside the `credentials` file.
3. Run ```terraform apply plandev.tfplan``` - this may take up to 15 minutes

Alternatively you can place variables inside `terraform.tfvars` file instead of pasting them into CLI input.

## Rollbacking setup
```
terraform destroy
```

## Manual deployment

There is a deploy .sh file in the repository which allows you to deploy your spring boot app to AWS services after they have been created using terraform.

Run:
```
sudo ./deploy.sh <appname> <environment> <region> <aws_account_id> <aws_access_key_id> <aws_secret_access_key> <ecr_repository_name> 
```

## Deployment through CircleCI

- The repository is configured in a way that once any change is pushed, a workflow is triggered which build, tags and pushes the docker image of the spring boot application into Dockerhub and then this docker image is stored in an S3 bucket which is then deployed onto an elastic beanstalk environment.

- The contents of this can be found in the .circleci/config.yml file.

- Deployment to prod is always put on hold until the developer verifies that the development environment is functioning correctly.

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

- Development [URL](http://pcs-app-dev.yfwwjakn29.us-east-1.elasticbeanstalk.com)
- Production [URL](http://pcs-app-prod.7sc6pmxnec.us-east-1.elasticbeanstalk.com)


## Usage

If running the REST API service through beanstalk, on either the development or production URL, hit these example URLs to see the REST API service in action. You can also append these at the end of the localhost url once run locally. (append these at the end of the url)

1) /dynamoDb/permission/level/U/movie/1 (200 OK)
2) /dynamoDb/permission/level/U/movie/6 (404 Movie not found)
3) /dynamoDb/permission/level/***/movie/5 (500 System error)

For more information on which end-points can be hit, append /swagger-ui.html at the end of either the localhost url or AWS beanstalk application url.

NOTE: When running with the zuul gateway, before running each end-point shown above, it would start with /movies/ then you can append the end-points given in the example.

## Movie Data

| Movie ID       | Level          | 
| ------------- |:-------------:| 
| 1      | U | 
| 2     | PG      |
| 3 | 12     |
| 4    | 15      | 
| 5 | 18      |

## Kibana
- It is important that application logs are monitored frequently and to do this, I created an Elasticsearch domain which pulls in Cloudwatch logs from the Elastic beanstalk application. Once pulled, the information is displayed in Kibana and the requests made in the application can be visualised in line graphs or time series etc.

- Access the Kibana dashboard [here](https://search-pcs-domain-wab3hwjugsxd2vokrtmgmoxufm.us-east-1.es.amazonaws.com/_plugin/kibana/app/kibana#/home?_g=())
## License
[MIT](https://choosealicense.com/licenses/mit/)
