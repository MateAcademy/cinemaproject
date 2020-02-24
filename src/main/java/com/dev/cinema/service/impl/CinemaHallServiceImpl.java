package com.dev.cinema.service.impl;

import com.dev.cinema.dao.CinemaHallDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.CinemaHallService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sergey Klunniy
 */
@Service
public class CinemaHallServiceImpl implements CinemaHallService {

    @Autowired
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        try {
            return cinemaHallDao.getAll();
        } catch (DataProcessingException e) {
            throw new RuntimeException("Cannot show cinema halls from database", e);
        }
    }

    @Override
    public CinemaHall getByIdCinemaHall(Long id) {
        return cinemaHallDao.getByIdCinemaHall(id);
    }

}
