package com.poc.booking.dao;

import com.poc.booking.model.Movie;
import com.poc.booking.model.TheatreShow;
import com.poc.booking.repo.MovieRepo;
import com.poc.booking.repo.TheatreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "theatreDao")
public class TheatreShowDao {
    @Autowired
    private TheatreRepo theatreRepo;

    @Autowired
    private MovieRepo movieRepo;

    public List<TheatreShow> getShowTheatres(TheatreShow theatreShow) {
        Movie movie = movieRepo.findMovieByName(theatreShow.getMovieName());
        if(movie.getStatus().equals("N"))
            return null;
        List<TheatreShow> theatreShows = theatreRepo.findAllByMovieCityDate(theatreShow.getMovieName(), theatreShow.getCityName(), theatreShow.getMovieDate());
        return theatreShows;
    }

    public DaoResponse addTheatreShow(TheatreShow theatreShow) {
        Movie movie = new Movie();
        DaoResponse response = new DaoResponse();
        if(theatreRepo.findOneByMovieCityDateStartTime(theatreShow.getMovieName(), theatreShow.getCityName(), theatreShow.getMovieDate(), theatreShow.getStartShowTime()) != null) {
            response.setStatus("Failed");
            response.setStatusMessage("Show Already Exists");
            return response;
        }
        if(movieRepo.findMovieByName(theatreShow.getMovieName()) == null) {
            movie.setId(theatreShow.getMovieId());
            movie.setMovieName(theatreShow.getMovieName());
            movie.setStatus("A");
            movieRepo.save(movie);
            movieRepo.flush();
        }

        theatreRepo.save(theatreShow);
        theatreRepo.flush();

        response.setStatus("Succeeded");
        response.setStatusMessage("Show Added Successfully with show Id "+ theatreShow.getId());
        return response;
    }

    public DaoResponse updateTheatreShow(TheatreShow theatreShow) {
        DaoResponse response = new DaoResponse();
        if(theatreRepo.findOneByMovieCityDateStartTime(theatreShow.getMovieName(), theatreShow.getCityName(), theatreShow.getMovieDate(), theatreShow.getStartShowTime()) == null) {
            response.setStatus("Failed");
            response.setStatusMessage("Show Doesn't Exists");
            return response;
        }

        theatreRepo.save(theatreShow);
        theatreRepo.flush();

        response.setStatus("Succeeded");
        response.setStatusMessage("Show Updated Successfully with show Id "+ theatreShow.getId());
        return response;
    }

    public DaoResponse deleteTheatreShow(TheatreShow theatreShow) {
        DaoResponse response = new DaoResponse();
        if(theatreRepo.findOneByMovieCityDateStartTime(theatreShow.getMovieName(), theatreShow.getCityName(), theatreShow.getMovieDate(), theatreShow.getStartShowTime()) == null) {
            response.setStatus("Failed");
            response.setStatusMessage("Show Doesn't Exists");
            return response;
        }
        theatreRepo.delete(theatreShow);
        theatreRepo.flush();

        response.setStatus("Succeeded");
        response.setStatusMessage("Show Updated Successfully with show Id "+ theatreShow.getId());
        return response;
    }
}
