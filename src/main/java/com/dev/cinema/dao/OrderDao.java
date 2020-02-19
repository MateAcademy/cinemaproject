package com.dev.cinema.dao;

import com.dev.cinema.model.Orders;
import com.dev.cinema.model.User;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public interface OrderDao {
    Orders create(Orders orders);

    List<Orders> getOrderHistory(User user);
}
