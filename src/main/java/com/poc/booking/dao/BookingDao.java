package com.poc.booking.dao;

import com.poc.booking.model.Booking;
import com.poc.booking.model.Movie;
import com.poc.booking.model.TheatreShow;
import com.poc.booking.repo.BookingRepo;
import com.poc.booking.repo.MovieRepo;
import com.poc.booking.repo.TheatreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository(value = "bookingDao")
public class BookingDao {
    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private TheatreRepo theatreRepo;

    @Autowired
    private MovieRepo movieRepo;

    public DaoResponse bookTicket(Booking bookingInfo) {
        DaoResponse response = new DaoResponse();

        TheatreShow show = theatreRepo.findOneByID(bookingInfo.getShowId());
        Movie movie = movieRepo.findOneByID(show.getMovieId());

        int n = reallocateSeats(show, bookingInfo);
        if(n == 0) {
            response.setStatus("Failed");
            response.setStatusMessage("no available seats");
            return response;
        }

        double cost = calculateCost(n, show, movie);
        bookingInfo.setCost(cost);

        theatreRepo.saveAndFlush(show);
        bookingRepo.saveAndFlush(bookingInfo);

        response.setStatus("Succeeded");
        response.setStatusMessage("Booking Done Successfully with booking id "+ bookingInfo.getId());
        return response;
    }

    public DaoResponse cancelBooking(Booking bookingInfo) {
        DaoResponse response = new DaoResponse();

        TheatreShow show = theatreRepo.findOneByID(bookingInfo.getShowId());
        reallocateSeats(bookingInfo, show);

        theatreRepo.saveAndFlush(show);
        bookingRepo.delete(bookingInfo);

        response.setStatus("Succeeded");
        response.setStatusMessage("Booking Done Successfully with booking id "+ bookingInfo.getId());
        return response;
    }

    private int reallocateSeats(Booking bookingInfo, TheatreShow show) {
        String availableSeats = show.getAvailableSeats();
        String bookedSeats = bookingInfo.getBookedSeats();
        availableSeats = availableSeats.substring(1,availableSeats.length()-1);
        bookedSeats = bookedSeats.substring(1,bookedSeats.length()-1);
        String bookedSeatsArr[] = bookedSeats.split(",");

        List<String> bookedSeatsList = Arrays.asList(bookedSeatsArr);
        List<String> availableSeatsList = Stream.of(availableSeats.split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        for (String seatNumber : bookedSeatsList)
            availableSeatsList.add(seatNumber);

        show.setAvailableSeats(availableSeatsList.toString());

        return availableSeatsList.size();
    }

    private int reallocateSeats(TheatreShow show, Booking bookingInfo) {
        String availableSeats = show.getAvailableSeats();
        String bookedSeats = bookingInfo.getBookedSeats();
        availableSeats = availableSeats.substring(1,availableSeats.length()-1);
        bookedSeats = bookedSeats.substring(1,bookedSeats.length()-1);
        String bookedSeatsArr[] = bookedSeats.split(",");

        List<String> bookedSeatsList = Arrays.asList(bookedSeatsArr);
        List<String> availableSeatsList = Stream.of(availableSeats.split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        if(bookedSeatsList.size() > availableSeatsList.size())
            return 0;

        for (String seatNumber : bookedSeatsList)
            availableSeatsList.remove(availableSeatsList.indexOf(seatNumber));

        show.setAvailableSeats(availableSeatsList.toString());
        bookingInfo.setBookedSeats(bookedSeatsList.toString());

        return bookedSeatsList.size();
    }

    private double calculateCost(int n, TheatreShow show, Movie movie) {
        double cost;
        if(n < 3){
            cost = movie.getTicketCost()*n;
        }
        else {
            double afternoonDiscount = (double) show.getAfternoonDiscount() /100;
            double thirdTicketDiscount = (double) show.getThirdTicketDiscount() /100;
            cost = movie.getTicketCost() * (2 - afternoonDiscount);
            cost += movie.getTicketCost() * (n-2 - afternoonDiscount - thirdTicketDiscount);
        }
        return cost;
    }
}
