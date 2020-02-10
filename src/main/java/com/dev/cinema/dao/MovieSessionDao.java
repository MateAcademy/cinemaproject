package com.dev.cinema.dao;

import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.MovieSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
public interface MovieSessionDao {

    MovieSession add (MovieSession movieSession);

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate showTime) throws DataProcessingException;
}
