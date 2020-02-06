package com.dev.cinema;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.dao.impl.UserDaoImpl;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.impl.AuthenticationServiceImpl;

import java.time.LocalDateTime;

/**
 * @author Sergey Klunniy
 */
public class Main {

    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {

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
        LocalDateTime showTime = LocalDateTime.of(2020, 02, 05, 18, 00);
        movieSession.setShowTime(showTime);
        movieSessionService.add(movieSession);
        movieSessionService.findAvailableSessions(1L,
                showTime.toLocalDate()).forEach(System.out::println);

        System.out.println(cinemaHall);

        User user = new User( "Sergey", "123",  "mate@gmail.com");

        AuthenticationService au = new AuthenticationServiceImpl();
        User user1 = au.register(user.getEmail(), user.getPassword());

        user.setSalt(user1.getSalt());
        user.setPassword(user1.getPassword());
        UserDao userDao = new UserDaoImpl();
        userDao.add(user);

        User user2 = userDao.findByEmail("mate@gmail.com");
        System.out.println(user2);
    }
}
