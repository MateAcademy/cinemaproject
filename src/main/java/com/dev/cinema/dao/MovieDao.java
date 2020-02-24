package com.dev.cinema.dao;

import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.Movie;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public interface MovieDao {

    Movie add(Movie movie);

    List<Movie> getAll() throws DataProcessingException;
    
    Movie getByIdMovie(Long id);
}
