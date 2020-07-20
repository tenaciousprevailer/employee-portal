# Employee Portal
Employee Portal application

#### Developer Details
Developed by `Ravi Kumar Soni`

Linked Profile: `https://www.linkedin.com/in/tenaciousprevailer`

Contact Info: `8955047966`

## Application Details
Developed Employee Resource, which supports below apis: 
1. get:  To get a specific employee
2. get all: To get all employees, supports pagination and sorting
3. create: Create a new Employee (supports Validation)
4. update: Update an existing employee (supports Validation)
5. delete: Delete an existing employee
6. search apis: Search for an employee using any field




## Tech Details
Application has:
1. MongoDb as Persistent Database
2. Elasticsearch for searching
3. Redis for Caching, backed by Redisson Implmentation
4. Swagger for API documentation : http://localhost:8080/api/swagger-ui.html 
5. Actuator, for API health & monitoring

### All apis have the below properties:
1. Resource(controller/endpoint) layer
2. Service layer
3. Repository layer for persistence
4. Repository layer for searching
5. Different Models for persisting data & for sending response (DTO)
6. Input Validation with proper validation failure response
7. Versioning in all APIs (URI versioning used)
8. Defined Exception Structure across the application
9. Failure Responses with Proper Exception details

### Supported Environments:
1. local (dev local)
2. test (test environment)
3. dev environment (dev deployed environment)
4. qa environment
5. uat (staging) environment
6. prod environment

## How to Start application
Invoke `./start.sh` to bring up the application.
The script brings up all the required containers up using `docker-compose`

## Required Use Cases (please refer swagger doc for more details):
1. Register an employee 
    
    Send `POST` Request to: http://localhost:8080/api/v1/employees
    
    Sample Payload
    
    ```
    {
      "departmentName": "Tech",
      "dob": "2000-07-20T08:09:38.389Z",
      "firstName": "firstName",
      "gender": "MALE",
      "lastName": "lastName"
    }
    ```

2. Get all the employee details in ascending order by first name

    Send `GET` Request to: http://localhost:8080/api/v1/employees?sort=firstName,asc
    

## TODO
Build the application into docker image
Create docker compose file for entire application
