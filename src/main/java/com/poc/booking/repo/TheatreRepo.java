package com.poc.booking.repo;

import com.poc.booking.model.TheatreShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository(value = "theatreRepo")
public interface TheatreRepo extends JpaRepository<TheatreShow,Long> {
    @Query("select t From Theatre_Show t where t.movieName=?1 AND t.cityName=?2 AND t.movieDate=?3")
    List<TheatreShow> findAllByMovieCityDate(String movie, String city, String date);

    @Query("select t From Theatre_Show t where t.movieName=?1 AND t.cityName=?2 AND t.movieDate=?3 AND t.startShowTime=?4")
    TheatreShow findOneByMovieCityDateStartTime(String movie, String city, String date, String startTime);

    @Query("select t From Theatre_Show t where t.id=?1")
    TheatreShow findOneByID(Long Id);
}
