package com.dev.cinema.service;

import com.dev.cinema.model.Movie;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public interface MovieService {

    Movie add(Movie movie);

    List<Movie> getAll();
}
