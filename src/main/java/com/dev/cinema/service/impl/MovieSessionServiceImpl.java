package com.dev.cinema.service.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sergey Klunniy
 */
@Service
public class MovieSessionServiceImpl implements MovieSessionService {

    @Autowired
    private MovieSessionDao movieSessionDao;
    
    @Override
    public MovieSession add(MovieSession movieSession) {
        return movieSessionDao.add(movieSession);
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try {
            return movieSessionDao.findAvailableSessions(movieId, date);
        } catch (DataProcessingException e) {
            throw new RuntimeException("Can't show movie sessions from database", e);
        }
    }

}
