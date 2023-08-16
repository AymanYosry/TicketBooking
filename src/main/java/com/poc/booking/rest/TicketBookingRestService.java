package com.poc.booking.rest;

import com.poc.booking.dao.BookingDao;
import com.poc.booking.dao.TheatreShowDao;
import com.poc.booking.model.Booking;
import com.poc.booking.model.TheatreShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TicketBookingRestService {
    @Autowired
    private TheatreShowDao theatreShowDao;

    @Autowired
    private BookingDao bookingDao;

    @PostMapping("/theatres")
    public List<TheatreShow> getShowTheatres(@RequestBody TheatreShow theatreShow) {
        return theatreShowDao.getShowTheatres(theatreShow);
    }

    @PostMapping("/addshow")
    public void addTheatreShow(@RequestBody TheatreShow theatreShow) {
        theatreShowDao.addTheatreShow(theatreShow);
    }

    @PutMapping("/updateshow")
    public void updateTheatreShow(@RequestBody TheatreShow theatreShow) {
        theatreShowDao.updateTheatreShow(theatreShow);
    }

    @DeleteMapping("/deleteshow")
    public void deleteTheatreShow(@RequestBody TheatreShow theatreShow) {
        theatreShowDao.addTheatreShow(theatreShow);
    }

    @PostMapping("/book")
    public void book(@RequestBody Booking bookingInfo) {
        bookingDao.bookTicket(bookingInfo);
    }

    @PostMapping("/cancel")
    public void cancel(@RequestBody Booking bookingInfo) {
        bookingDao.cancelBooking(bookingInfo);
    }
}
