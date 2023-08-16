package com.poc.booking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "Booking")
@Data
public class Booking implements Serializable {
    /*
    @ManyToOne
    @JoinColumn(name = "Booking_Show_Id")
    private TheatreShow show;
    */
    @Id
    @Column(name = "Id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "Show_Id", updatable = false, nullable = false)
    private Long showId;

    @Column(name = "Booked_Seats", nullable = false)
    private String bookedSeats;

    @Column(name = "Cost", nullable = false)
    private double cost;
}
