package com.dev.cinema.controller;

import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sergey Klunniy
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getUserList(Model model) {
        model.addAttribute("userModel", new User());
        model.addAttribute("users", userService.getUserList());
        return "user";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("userModel") User user) {
        userService.add(user);
        return "redirect:/";
    }

}
