package com.dev.cinema.controllers;

import com.dev.cinema.dto.OrderRequestDto;
import com.dev.cinema.dto.OrderResponseDto;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sergey Klunniy
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping(value = "/complete")
    public String orderRequestDto(@RequestBody OrderRequestDto orderRequestDto) {
        List<Ticket> tickets = shoppingCartService
                .getByUser(userService.getUserById(orderRequestDto.getUserId())).getTickets();
        User user = userService.getUserById(orderRequestDto.getUserId());
        orderService.completeOrder(tickets, user);
        return "Order complete success";
    }

    @GetMapping(value = "/getOrderById")
    public List<OrderResponseDto> getOrderById(Long userId) {
        User user = userService.getUserById(userId);
        List<Order> orders = orderService.getOrderHistory(user);
        List<OrderResponseDto> ldto = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            OrderResponseDto dto = new OrderResponseDto();
            dto.setTickets(orders.get(i).getTickets());
            dto.setUserEmail(orders.get(i).getUser().getEmail());
            ldto.add(dto);
        }
        return ldto;
    }
}
