package com.poc.booking.repo;

import com.poc.booking.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "movieRepo")
public interface MovieRepo extends JpaRepository<Movie,Long> {
    @Query("select m from Movie m where m.movieName=?1 AND m.status=?2")
    Movie findMovieByName(String movieName);

    @Query("select m From Movie m where m.id=?1")
    Movie findOneByID(Long Id);
}
