package com.dev.cinema.service.impl;

import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.service.OrderService;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private static OrderDao orderDao;

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        return orderDao.completeOrder(tickets, user);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getOrderHistory(user);
    }
}
