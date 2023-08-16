package com.poc.booking.rest;

import com.poc.booking.dao.BookingDao;
import com.poc.booking.dao.DaoResponse;
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
    public DaoResponse addTheatreShow(@RequestBody TheatreShow theatreShow) {
        return theatreShowDao.addTheatreShow(theatreShow);
    }

    @PutMapping("/updateshow")
    public DaoResponse updateTheatreShow(@RequestBody TheatreShow theatreShow) {
        return theatreShowDao.updateTheatreShow(theatreShow);
    }

    @DeleteMapping("/deleteshow")
    public DaoResponse deleteTheatreShow(@RequestBody TheatreShow theatreShow) {
        return theatreShowDao.addTheatreShow(theatreShow);
    }

    @PostMapping("/book")
    public DaoResponse book(@RequestBody Booking bookingInfo) {
        return bookingDao.bookTicket(bookingInfo);
    }

    @PostMapping("/cancel")
    public DaoResponse cancel(@RequestBody Booking bookingInfo) {
        return bookingDao.cancelBooking(bookingInfo);
    }
}
