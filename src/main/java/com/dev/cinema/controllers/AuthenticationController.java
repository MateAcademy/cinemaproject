package com.dev.cinema.controllers;

import com.dev.cinema.dto.UserRequestDto;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sergey Klunniy
 */
@RestController
@RequestMapping("/")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public String login(@RequestBody UserRequestDto requestDt) {
        User user = userService.findByEmail(requestDt.getEmail());
        if (user == null) {
            return "ERROR, this user is not in the database";
        } else {
            return "SUCCESS, this user is present in the database";
        }
    }

    @PostMapping("/register")
    public boolean register(@RequestBody UserRequestDto requestDt) {
        User user = userService.findByEmail(requestDt.getEmail());
        if (user == null) {
            authenticationService.register(requestDt.getEmail(), requestDt.getPassword());
            return true;
        } else {
            return false;
        }
    }
}
