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
