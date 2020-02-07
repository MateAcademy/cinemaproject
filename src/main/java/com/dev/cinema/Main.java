package com.dev.cinema;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.dao.impl.UserDaoImpl;
import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.*;
import com.dev.cinema.service.*;
import com.dev.cinema.service.impl.AuthenticationServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
public class Main {

    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) throws AuthenticationException {

        MovieService movieService =
                (MovieService) injector.getInstance(MovieService.class);
        Movie movie = new Movie();
        movie.setTitle("Family instant");
        movie.setDescription("new film 2020");
        movie = movieService.add(movie);

        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(50);
        cinemaHall = cinemaHallService.add(cinemaHall);

        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHall);
        movieSession.setMovie(movie);
        LocalDateTime showTime = LocalDateTime.of(2020, 02, 07, 10, 00);
        movieSession.setShowTime(showTime);
        movieSessionService.add(movieSession);
        movieSessionService.findAvailableSessions(1L,
                showTime.toLocalDate()).forEach(System.out::println);

        System.out.println(cinemaHall);

        List<MovieSession> availableSessions = movieSessionService
                .findAvailableSessions(movie.getId(), LocalDate.now());
        availableSessions.forEach(System.out::println);

        //User selects the session and processing the order

        //Before this we need to register the user
        AuthenticationService authenticationService = (AuthenticationService)
                injector.getInstance(AuthenticationService.class);
        authenticationService.register("sergey@gmail.com", "password");

        User user = authenticationService.login("sergey@gmail.com", "password");

        ShoppingCartService busketService = (ShoppingCartService)
                injector.getInstance(ShoppingCartService.class);

        MovieSession selectedMovieSession = availableSessions.get(0);
        busketService.addSession(selectedMovieSession, user);
        ShoppingCart userBucket = busketService.getByUser(user);
        System.out.println(userBucket);

//        User user2 = authenticationService.register("i@i.ua", "pass");
//        busketService.registerNewShoppingCart(user2);
    }
}
