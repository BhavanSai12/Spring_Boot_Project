﻿# Project : Ticket-Booking-Application

[Spring Boot Complete Guide](https://medium.com/@toimrank/spring-boot-complete-guide-c5162b0985f3)

# Spring Boot
Spring Boot is a extended version of Spring framework which provides a capability to develop a project with more feature in less time.

There are multiple things Spring Boot project provides.
 - Spring Boot initializer which help us to create a project in less than 10 min.
 - Spring Boot starter projects helps us to define all dependencies for our projects.
 - Spring Boot DevTools helps us to deploy Spring Boot application without server restart.

Spring Boot follows a layered architecture where each layer communicate with other required layer. There are four layers in Spring boot:
- Presentation Layer: This layer use to present JSON on front end and authenticate end users.
- Business Layer: This layer is all about writing business logic and things related to authorization and authentication.
- Integration Layer: This layer is responsible for storage logic and convert business objects from and to database rows.
- Data Layer: As name suggest it is related to data and perform operation such as CRUD (create, retrieve, update, delete).


Spring boot application code base mainly have four major modules:

 1. Java Packages
 2. application.properties file
 3. pom.xml file
 4. Spring Boot main Java application File

### Java Packages

It is used organize all the classes and interfaces related to application. Packages can have any custom name with all lower case to avoid conflicts. All Java packages gets created inside src/main/java folder.

Below are the main packages required for any of the Spring boot application:

***com.example.demo.controller***
This package will contain all the controller classes.

***com.example.demo.bean***
This package will contain all the beans class with private variable and setter getter method.

***com.example.demo.dao***
This package will contain all the database persistence logic classes and interfaces.

 ***com.example.demo.service***
The package will contain classes and interfaces having business logic related to project.


### application.properties File

This file is to declare project level properties or configurations. It can be for both built-in and custom classes. application.properties file located inside src/main/resources folder.

Creating project using STS tool or Spring initializr by default will create application.properties file.

Below is the example to declare configurations related to Spring database inside application.properties.

### pom.xml File Dependencies 

pom.xml file is to declare required Spring boot dependencies. This file by default gets created at the time of project creation using STS tool or Spring initializr.

The main advantage of Spring boot pom.xml file as it is not require to declare version for any of the pom dependency under <dependencies> tag. It will automatically include depending on Spring Boot Starter Parent version dependency.

Below pom dependency as Spring Boot Starter Parent helps us to manage all other Spring Boot Starters dependencies included as part of <dependencies> tag as shown below.

  
### Spring Boot main Java application File

Below is the main Java application file which helps us to start Spring Boot Application having @SpringBootApplication as an annotation on top of the class with main methods defined.

  
  
  
  
  
  
## Annotations
  
1.  **@RestController** annotation used to created RESTful web services which can be accessed using http methods like GET, POST, etc. and it is the combination of @Controller and @ResponseBody.

2. **@RequestMapping** annotation is used to define header, uri, method type, parameters, path, consume type, etc.

3. **@Autowired** annotation is to consume service create as part of PersonServide.java class using @Service annotation.

4. **@RequestParam** is used to collect all parameters expecting from request. Parameter can be declare as optional also using required attribute as false mentioned at line number 19.

5. **@PathVariable** used to collect value from request URI as mentioned from line number 24 to 27.

6. **@GetMapping** used to map HTTP GET request. It mainly used to fetch/get data from server.

7. **@PostMapping** used to map HTTP POST request. It mainly used to create a new record in the database.

8. **@PutMapping** used to map HTTP PUT request. It mainly used to update record data in the database.

9. **@DeleteMapping** used to map HTTP DELETE request. It mainly used to delete record data from the database.
  
  
  
