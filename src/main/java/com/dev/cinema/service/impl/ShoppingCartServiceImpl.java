package com.dev.cinema.service.impl;

import com.dev.cinema.dao.ShoppingCartDao;
import com.dev.cinema.dao.TicketDao;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.service.ShoppingCartService;

/**
 * @author Sergey Klunniy
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Inject
    private static ShoppingCartDao shoppingCartDao;
    @Inject
    private static TicketDao ticketDao;

    @Override
    public void addSession(MovieSession session, User user) {
        Ticket ticket = new Ticket();
        ticket.setCinemaHall(session.getCinemaHall());
        ticket.setMovie(session.getMovie());
        ticket.setUser(user);
        ticket.setShowTime(session.getShowTime());

        ShoppingCart usersShoppingCart = shoppingCartDao.getByUser(user);
        usersShoppingCart.getTickets().add(ticketDao.add(ticket));
        shoppingCartDao.update(usersShoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCartDao.clear(shoppingCart);
    }
}
