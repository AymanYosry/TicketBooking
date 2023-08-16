package com.poc.booking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "Theatre_Show")
@Data
public class TheatreShow implements Serializable {
    @Id
    @Column(name = "Id")
    private Long id;

    @Column(name = "Movie_Id")
    private Long movieId;

    @Column(name = "Movie_Name")
    private String movieName;

    @Column(name = "Theatre_Name")
    private String theatreName;

    @Column(name = "City_Name")
    private String cityName;

    @Column(name = "Movie_Date")
    private String movieDate;

    @Column(name = "Start_Show_Time")
    private String startShowTime;

    @Column(name = "End_Show_Time")
    private String endShowTime;

    @Column(name = "Available_Seats")
    private String availableSeats;

    @Column(name = "Third_Ticket_Discount")
    private int thirdTicketDiscount;

    @Column(name = "Afternoon_Discount")
    private int afternoonDiscount;
}
