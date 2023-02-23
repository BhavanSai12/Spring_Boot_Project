package com.bhavan.TicketBookingApplication.model;

import jakarta.persistence.*;

import java.util.Date;

/*
* Model Nothing But Entity
*
* This class become model class, and it will create table with class Name,i.e. Ticket*/
@Entity
@Table(name = "tbl_ticket")//This is optional, and it will create a table with tbl_ticket name
public class Ticket {
    @Id    //Primary Key
    @GeneratedValue(strategy = GenerationType.AUTO)
    //This will create a sequence on the database and this sequence will increment when ever you insert row.
    @Column(name = "ticket_id")
    private Integer ticketId;
    @Column(name = "Passenger_name")//This is also optional which will keep passenger_name
    private String passengerName; //Every Property is a column to the table tbl_ticket
                                    //By default, property name will be column name
    @Column(name = "source_station")
    private String sourceStation;
    @Column(name = "destination_station")
    private String destinationStation;
    @Column(name = "travel_date")
    private Date travelDate;

    private String email;

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getSourceStation() {
        return sourceStation;
    }

    public void setSourceStation(String sourceStation) {
        this.sourceStation = sourceStation;
    }

    public String getDestinationStation() {
        return destinationStation;
    }

    public void setDestinationStation(String destinationStation) {
        this.destinationStation = destinationStation;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Ticket(String passengerName, String sourceStation, String destinationStation, Date travelDate, String email) {
        this.passengerName = passengerName;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
        this.travelDate = travelDate;
        this.email = email;
    }

    public Ticket() {
    }
}
