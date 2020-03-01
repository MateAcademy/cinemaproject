package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.OrderRequestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sergey Klunniy
 */
@RestController
@RequestMapping("/security")
public class SecurityExample {

    @GetMapping
    public String security() {
        return "Hello Spring Security";
    }

    @PostMapping
    public String addOrder(@RequestBody OrderRequestDto orderRequestDto) {
        System.out.println(orderRequestDto);
        return "Success";
    }
}
