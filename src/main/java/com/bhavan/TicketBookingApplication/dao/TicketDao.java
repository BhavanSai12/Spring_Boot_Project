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
