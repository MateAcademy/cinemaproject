package com.dev.cinema.controllers;

import com.dev.cinema.dto.MovieSessionRequestDto;
import com.dev.cinema.dto.ShoppingCartResponseDto;
import com.dev.cinema.dto.TicketResponseDto;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.time.LocalDateTime;
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
@RequestMapping("/shoppingCarts")
public class ShoppingCartController {

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private CinemaHallService cinemaHallService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/addMovieSession")
    public void addMovieSession(@RequestBody MovieSessionRequestDto movieSessionRequestDto,
                                @RequestParam Long userId) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService.getByIdMovie(movieSessionRequestDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService.getByIdCinemaHall(movieSessionRequestDto
                .getCinemaHallId()));
        movieSession.setShowTime(LocalDateTime.parse(movieSessionRequestDto.getShowTime()));
        User user = userService.getUserById(userId);
        shoppingCartService.addSession(movieSession, user);
    }

    @GetMapping("/getByUserId")
    public ShoppingCartResponseDto getByUserId(@RequestParam Long userId) {
        return transformToShoppingCartResponseDto(
                shoppingCartService.getByUser(userService.getUserById(userId)));
    }

    private ShoppingCartResponseDto transformToShoppingCartResponseDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setUserEmail(shoppingCart.getUser().getEmail());
        shoppingCartResponseDto.setTickets(shoppingCart.getTickets()
                .stream()
                .map(this::transformToTicketDto)
                .collect(Collectors.toList()));
        return shoppingCartResponseDto;
    }

    private TicketResponseDto transformToTicketDto(Ticket ticket) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setUserEmail(ticket.getUser().getEmail());
        ticketResponseDto.setCinemaHallId(ticket.getCinemaHall().getId());
        ticketResponseDto.setMovieTitle(ticket.getMovie().getTitle());
        ticketResponseDto.setShowTime(ticket.getShowTime().toString());
        return ticketResponseDto;
    }
}
