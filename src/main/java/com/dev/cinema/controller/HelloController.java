package com.dev.cinema.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sergey Klunniy
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

}
