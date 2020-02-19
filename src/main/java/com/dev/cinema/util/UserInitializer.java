package com.dev.cinema.util;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.*;
import com.dev.cinema.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
@Component
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
    private ShoppingCartService busketService;

    @PostConstruct
    public void initUser() {

        Movie movie = new Movie();
        movie.setTitle("Family instant");
        movie.setDescription("new film 2020");
        movie = movieService.add(movie);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(50);
        cinemaHall = cinemaHallService.add(cinemaHall);

        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        LocalDateTime showTime = LocalDateTime.of(2020, 02, 10, 10, 00);
        movieSession.setShowTime(showTime);

        movieSessionService.add(movieSession);

        movieSessionService.findAvailableSessions(1L,
                showTime.toLocalDate()).forEach(System.out::println);

        System.out.println(cinemaHall);

        List<MovieSession> availableSessions = movieSessionService
                .findAvailableSessions(movie.getId(), showTime.toLocalDate());
        availableSessions.forEach(System.out::println);

        authenticationService.register("sergey@gmail.com", "password");

        User user = null;
        try {
            user = authenticationService.login("sergey@gmail.com", "password");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        MovieSession selectedMovieSession = availableSessions.get(0);
        busketService.addSession(selectedMovieSession, user);
        ShoppingCart userBucket = busketService.getByUser(user);
        System.out.println(userBucket);
    }
}
