package com.dev.cinema.service.impl;

import com.dev.cinema.dao.CinemHallDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.service.CinemaHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
@Service
public class SinemaHallServiceImpl implements CinemaHallService {

    @Autowired
    private static CinemHallDao cinemHallDao;

    @Override
    @Transactional
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemHallDao.add(cinemaHall);
    }

    @Override
    @Transactional
    public List<CinemaHall> getAll() {
        try {
            return cinemHallDao.getAll();
        } catch (DataProcessingException e) {
            throw new RuntimeException("Cannot show cinema halls from database", e);
        }
    }

}
