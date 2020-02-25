package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sergey Klunniy
 */
@RestController
@RequestMapping("/movieSession")
public class MovieSessionController {

    @Autowired
    private MovieSessionService movieSessionService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private CinemaHallService cinemaHallService;

    @PostMapping("/addMovieSession")
    public String addMovieSession(@RequestBody MovieSessionRequestDto movieSessionRequestDto) {
        Movie movie = movieService.getByIdMovie(movieSessionRequestDto.getMovieId());
        CinemaHall cinemaHall = cinemaHallService
                .getByIdCinemaHall(movieSessionRequestDto.getCinemaHallId());
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setShowTime(LocalDateTime.parse(movieSessionRequestDto.getShowTime()));
        movieSessionService.add(movieSession);
        return "We add movie to movieSession: " + movieSession;
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAllMovieSessions(@RequestParam Long movieId,
                                                             @RequestParam String showTime) {

        return movieSessionService.findAvailableSessions(movieId, LocalDate.parse(showTime))
                .stream()
                .map(this::getMovieSessionResponseDto)
                .collect(Collectors.toList());
    }

    private MovieSessionResponseDto getMovieSessionResponseDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        movieSessionResponseDto.setMovieSessionId(movieSession.getId());
        movieSessionResponseDto.setMovieId(movieSession.getMovie().getId());
        movieSessionResponseDto.setShowTime(movieSession.getShowTime().toString());
        return movieSessionResponseDto;
    }
}
