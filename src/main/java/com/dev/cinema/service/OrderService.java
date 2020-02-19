package com.dev.cinema.service;

import com.dev.cinema.model.Orders;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public interface OrderService {
    Orders completeOrder(List<Ticket> tickets, User user);

    List<Orders> getOrderHistory(User user);
}
