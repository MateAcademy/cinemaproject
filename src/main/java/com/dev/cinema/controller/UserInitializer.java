package com.dev.cinema.controller;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sergey Klunniy
 */
@RestController
public class UserInitializer {

    @Autowired
    private MovieService movieService;

    @Autowired
    private CinemaHallService cinemaHallService;

    @Autowired
    private MovieSessionService movieSessionService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @GetMapping("/inject")
    public void initUser() {

        Movie movie = new Movie();
        movie.setTitle("Family instant");
        movie.setDescription("new film 2020");
        movie = movieService.add(movie);

        authenticationService.register("sergey@gmail.com", "password");

        User user = null;
        try {
            user = authenticationService.login("sergey@gmail.com", "password");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(50);
        cinemaHall.setDescription("red");
        cinemaHall = cinemaHallService.add(cinemaHall);

        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        LocalDateTime showTime = LocalDateTime.of(2020, 02, 10, 10, 00);
        movieSession.setShowTime(showTime);

        movieSessionService.add(movieSession);

        movieSessionService.findAvailableSessions(1L,
                showTime.toLocalDate()).forEach(System.out::println);

        List<MovieSession> availableSessions = movieSessionService
                .findAvailableSessions(movie.getId(), showTime.toLocalDate());
        availableSessions.forEach(System.out::println);

        MovieSession selectedMovieSession = availableSessions.get(0);
        shoppingCartService.addSession(selectedMovieSession, user);
        ShoppingCart userBucket = shoppingCartService.getByUser(user);

    }
}
