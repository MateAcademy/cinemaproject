package com.dev.cinema.service;

import com.dev.cinema.model.MovieSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
public interface MovieSessionService {

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession session);
}
