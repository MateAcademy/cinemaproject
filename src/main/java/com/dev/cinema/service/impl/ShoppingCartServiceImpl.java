package com.dev.cinema.service.impl;

import com.dev.cinema.dao.ShoppingCartDao;
import com.dev.cinema.dao.TicketDao;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.service.ShoppingCartService;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sergey Klunniy
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Autowired
    private TicketDao ticketDao;

    @Override
    @Transactional
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
    @Transactional
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    @Transactional
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    @Transactional
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.setTickets(new ArrayList<Ticket>());
        shoppingCartDao.update(shoppingCart);
    }

}
