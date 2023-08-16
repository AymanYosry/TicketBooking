package com.poc.booking.repo;

import com.poc.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "bookingRepo")
public interface BookingRepo extends JpaRepository<Booking,Long> {
}
