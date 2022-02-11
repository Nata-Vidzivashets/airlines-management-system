# Airlines management system
## How to start
* Clone this project
* Create empty database
* Replace url, username, password with your information in application.properties file
in src/main/resources folder
* Inject test data: http://localhost:8080/inject
* Also, you can use postman collection in src/main/resources/data folder

## Setup by docker
Execute commands in the terminal:
* mvn clean package
* docker-compose up

For docker use port 6868, http://localhost:6868/inject
