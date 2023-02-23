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
