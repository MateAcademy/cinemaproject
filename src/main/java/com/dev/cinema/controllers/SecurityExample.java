package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.OrderRequestDto;
import org.springframework.web.bind.annotation.*;

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
