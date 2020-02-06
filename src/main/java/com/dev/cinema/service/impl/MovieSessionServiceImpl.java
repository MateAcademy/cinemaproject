package com.dev.cinema.service.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.MovieSessionService;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
@Service
public class MovieSessionServiceImpl implements MovieSessionService {

    @Inject
    private static MovieSessionDao movieSessionDao;
    
    @Override
    public MovieSession add(MovieSession movieSession) {
        return movieSessionDao.add(movieSession);
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try {
            return movieSessionDao.findAvailableSessions(movieId, date);
        } catch (DataProcessingException e) {
            throw new RuntimeException("Cannot show movie sessions from database", e);
        }
    }
}
