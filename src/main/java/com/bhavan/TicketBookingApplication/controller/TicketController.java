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
