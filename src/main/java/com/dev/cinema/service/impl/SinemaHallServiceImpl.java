package com.dev.cinema.service.impl;

import com.dev.cinema.dao.CinemHallDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.CinemaHallService;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
@Service
public class SinemaHallServiceImpl implements CinemaHallService {

    @Inject
    private static CinemHallDao cinemHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        try {
            return cinemHallDao.getAll();
        } catch (DataProcessingException e) {
            throw new RuntimeException("Cannot show cinema halls from database", e);
        }
    }
}
