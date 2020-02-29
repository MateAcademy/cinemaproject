package com.dev.cinema.controllers;

import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.UserRequestDto;
import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.service.AuthenticationService;
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
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/inject")
    public String inject() {
        authenticationService.register("ava@gmail.com", "111");
        authenticationService.register("sergei@mail.ru", "222");
        authenticationService.register("marfuck@kiev.ua", "333");
        authenticationService.register("fbi@usa.gov", "444");
        return "Data successfully injected";
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        List<UserResponseDto> dtos = new ArrayList<>();
        for (User user: userService.getUserList()) {
            UserResponseDto dto = new UserResponseDto();
            dto.setEmail(user.getEmail());
            dtos.add(dto);
        }
        return dtos;
    }

    @PostMapping
    public void create(@RequestBody UserRequestDto requestDto) {
        authenticationService.register(requestDto.getEmail(), requestDto.getPassword());
    }

    @GetMapping(value = "/email")
    public UserResponseDto getUserByEmail(String email) {
        User user = userService.findByEmail(email);
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }
}
