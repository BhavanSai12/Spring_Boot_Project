# Project : Ticket-Booking-Application
 ## prerequisites:
 - JDK latest / older vesion.
 - any IDE(STS,Intelij,Elicpse )
 

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
```
package com.bhavan.TicketBookingApplication.controller;

import com.bhavan.TicketBookingApplication.model.Ticket;
import com.bhavan.TicketBookingApplication.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/tickets")
public class TicketController {
    /*
    * REST SERVICES==> ITS AN APPROACH FOR WEBSERVICES
    * WEBSERIVCES==>EXCHANGE OF DATA BETWEEN TWO DIFFERENT FRAMEWORKS
    *
    * GET==>HTTP GET CALL
    * POST==> HTTP POST CALL
    * PUT==>UPDATE
    * DELETE==>DELETE ==>ALL THESE VERBS ARE CREATED IN JAVA USING JAX-RS REST SERVICE FRAMEWORK
    *
    * @Get+@ResquestMapping==>@GetMapping==>Retrieving the data
    * @Post+@ResquestMapping==>@PostMapping==>Creating the data
    * @Delete+ @RequestMapping ==> @DeleteMapping ==> Deleting the data
    * Angular/React communicates with Spring Boot==>It has Jackson Library ==>Java to Json and Json to java.
    *  REST SERVICES USES JSON ==> Javascript Object
    * {
    *   key1: value1,
        key2: value2,
        .....
        orderid:101,
        payment:2000,
        product:"Mobile"
        rating:4
     */


    //Retrieve Ticket
    @Autowired
    private TicketService ticketService;
    @GetMapping(value = "/all")
    public Iterable<Ticket>getAllTicket(){
        return ticketService.findAllTickets();
    }
    //Create Ticket
    @PostMapping(value = "/create")
    public Ticket createTicket(@RequestBody Ticket ticketObj){
//        ticketObj.setTravelDate(new Date());
        return ticketService.createTicket(ticketObj);
    }
    //Retrieve Single Ticket
    @GetMapping(value = "/{ticketId}")
    public Ticket getTicket(@PathVariable("ticketId")Integer ticketId){
        return ticketService.findTicket(ticketId);
    }
    //update Ticket
    public Ticket updateTicket(@PathVariable("ticketId")Integer ticketId,@PathVariable("newEmail")String newEmail){
        return ticketService.updateTicket(ticketId,newEmail);
    }
    //DeleteTicket
    @DeleteMapping(value = "/{ticketId}")
    public String deleteTicket(@PathVariable("ticketId")Integer ticketId){
        ticketService.deleteTicket(ticketId);
        /// give Statement.
        return "Ticket id " + ticketId + " has been removed.";
    }
}
```

***com.example.demo.bean***
This package will contain all the beans class with private variable and setter getter method.

***com.example.demo.dao***
This package will contain all the database persistence logic classes and interfaces.
```
package com.bhavan.TicketBookingApplication.dao;

import com.bhavan.TicketBookingApplication.model.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//We will use TicketDao for all database operations to tbl_ticket==> CRUD Operations on tbl_ticket
@Repository//This will take care of database connection
public interface TicketDao extends CrudRepository<Ticket,Integer> {
    /*
    * Spring Boot created internally CRUD Repository for achieving CRUD Operation
    * CrudRepository ==> 2 inputs parameters==> Model class,Data Type of Primary key
    * C==>CREATE==>INSERT STATEMENT==>save
    * R==>READ ==>SELECT STATEMENT==>findAll,findById
    * U==>UPDATE==>AlTER /UPDATE==>save
    * D==>DELETE==> DELETE /DROP==>deleteById
    * */
}
```

 ***com.example.demo.service***
The package will contain classes and interfaces having business logic related to project.
```
package com.bhavan.TicketBookingApplication.service;

import com.bhavan.TicketBookingApplication.dao.TicketDao;
import com.bhavan.TicketBookingApplication.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
/*THis annotation will consider as service layer for achieving transaction management and all other
external frameworks like RabbitMQ,kafka,JMS etc... instantiation. */
public class TicketService {
    //need to call TicketDao
    //class which implements ticketdao
    @Autowired
    private TicketDao ticketDao;//IOC==> TicketDao ticketDao=ioc.getTicketDao();

    //Retrieve All Tickets
    public Iterable<Ticket>findAllTickets(){
        return ticketDao.findAll();
        //findAll==> select* from tbl_project==>Resultset==>Iterable<Ticket>
    }
    //Retrieving Single Ticket
    public Ticket findTicket(Integer ticketId){
        return ticketDao.findById(ticketId).orElse(new Ticket());
        //findById==>select* from tbl_ticket where ticket_id=ticketId
        //Resultset can be converted Ticket.java
        // if ticketid data is not there ==>orElse will be invoked
    }

    //Inserting the data
    public Ticket createTicket(Ticket ticketObj){
        return ticketDao.save(ticketObj);
        //save==> insert into tbl_ticket values(....);
        /*
        * save is the common method for inserting the data or updating the data
        * insert ==> when ticketObj.ticketId==null
        *           when ticketObj.ticketId !=mull this id not there in the database
        *
        * Update==> when ticketObj.ticketId!=null this id there in the database
        *
        * */
    }
    //Updating The data
    public Ticket updateTicket(Integer ticketId,String newEmail){
        Ticket dbTicketObj=findTicket(ticketId);
        dbTicketObj.setEmail((newEmail));
        return ticketDao.save(dbTicketObj);
        //Here save Method will Update.
        // Update tbl_ticket set ...... where ticket_id=ticketId

    }
    //Delete the Single Record
    public void deleteTicket(Integer ticketId){
        ticketDao.deleteById(ticketId);
        //deleteById==> delete tbl_ticket where ticket_id=ticketId
    }
}

```


### application.properties File

This file is to declare project level properties or configurations. It can be for both built-in and custom classes. application.properties file located inside src/main/resources folder.

Creating project using STS tool or Spring initializr by default will create application.properties file.

Below is the example to declare configurations related to Spring database inside application.properties.
```

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/ticketdata
spring.datasource.username = root
spring.datasource.password = 12345
spring.jpa.show-sql = true
spring.jpa.hibernate.ddl-auto = update
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect
#spring.jpa.properties.hibernate.format_sql=true
```

### pom.xml File Dependencies 

pom.xml file is to declare required Spring boot dependencies. This file by default gets created at the time of project creation using STS tool or Spring initializr.

The main advantage of Spring boot pom.xml file as it is not require to declare version for any of the pom dependency under <dependencies> tag. It will automatically include depending on Spring Boot Starter Parent version dependency.

Below pom dependency as Spring Boot Starter Parent helps us to manage all other Spring Boot Starters dependencies included as part of <dependencies> tag as shown below.
 ```
 <?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.0.2</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.bhavan</groupId>
	<artifactId>TicketBookingApplication</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>TicketBookingApplication</name>
	<description>Demo project for Spring Boot</description>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<!--<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
		</dependency>-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
			<scope>runtime</scope>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
 ```

  
### Spring Boot main Java application File

Below is the main Java application file which helps us to start Spring Boot Application having @SpringBootApplication as an annotation on top of the class with main methods defined.
```
 package com.bhavan.TicketBookingApplication;

import com.bhavan.TicketBookingApplication.model.Ticket;
import com.bhavan.TicketBookingApplication.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class TicketBookingApplication implements CommandLineRunner {
	@Autowired
	private TicketService ticketService;

	public static void main(String[] args) {
		SpringApplication.run(TicketBookingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Ticket inputObj=new Ticket();
		inputObj.setPassengerName("Bhavan");
		inputObj.setSourceStation("Rajahmundry");
		inputObj.setDestinationStation("Hyderabad");
		inputObj.setTravelDate(new Date());
		inputObj.setEmail("bhavansai@gmail");

		ticketService.createTicket(inputObj);
	}
}
 ```
  
  
  
  
  
  
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
  
  
  
