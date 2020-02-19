package com.dev.cinema.service.impl;

import com.dev.cinema.dao.MovieDao;
import com.dev.cinema.model.Movie;
import com.dev.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;

    @Override
    @Transactional
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    @Transactional
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
