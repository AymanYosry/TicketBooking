package com.poc.booking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "Movie")
@Data
public class Movie implements Serializable {
    @Id
    @Column(name = "Id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "Movie_Name", nullable = false)
    private String movieName;

    @Column(name = "Status", nullable = false)
    private String status;

    @Column(name = "Ticket_Cost", nullable = false)
    private int ticketCost;
}
