package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.UserResponseDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sergey Klunniy
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @ResponseBody
    @GetMapping("/userDto")
    public UserResponseDto getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println(context.hashCode());

        Authentication authentication = context.getAuthentication();
        authentication.getAuthorities();

        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            UserDetails details = (UserDetails) principal;
            System.out.println(details.getUsername());
        }

        UserResponseDto dto = new UserResponseDto();
        dto.setFirstName("Bob");
        dto.setEmail("rus@rambler.ru");
        return dto;
    }
}
