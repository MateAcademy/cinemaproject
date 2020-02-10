package com.dev.cinema.dao;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public interface OrderDao {
    Order create(Order order);

    List<Order> getOrderHistory(User user);
}
