package com.dev.cinema.controllers;

import com.dev.cinema.model.Movie;
import com.dev.cinema.model.dto.MovieRequestDto;
import com.dev.cinema.model.dto.MovieResponceDto;
import com.dev.cinema.service.MovieService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sergey Klunniy
 */
@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public String addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        Movie movie = createMovieFromDto(movieRequestDto);
        movieService.add(movie);
        return "Data successfully injected" + movie;
    }

    private Movie createMovieFromDto(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.getTitle());
        movie.setDescription(movieRequestDto.getDescription());
        return movie;
    }

    @GetMapping
    public List<MovieResponceDto> getAllMovies() {
        List<MovieResponceDto> mdto = createListMovieResponceDto();
        return mdto;
    }

    private List<MovieResponceDto> createListMovieResponceDto() {
        List<Movie> allMovies = movieService.getAll();
        List<MovieResponceDto> mdto = new ArrayList<>();
        for (Movie movie : allMovies) {
            MovieResponceDto dto = new MovieResponceDto();
            dto.setTitle(movie.getTitle());
            dto.setDescription(movie.getDescription());
            mdto.add(dto);
        }
        return mdto;
    }
}
