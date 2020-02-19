package com.dev.cinema.service.impl;

import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.model.Orders;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sergey Klunniy
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    @Transactional
    public Orders completeOrder(List<Ticket> tickets, User user) {
        Orders orders = new Orders(tickets, user);
        return orderDao.create(orders);
    }

    @Override
    @Transactional
    public List<Orders> getOrderHistory(User user) {
        return orderDao.getOrderHistory(user);
    }
}
