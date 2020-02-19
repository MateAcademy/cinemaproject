package com.dev.cinema.controller;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Sergey Klunniy
 */
@Controller
public class UserController {

  @Autowired
  private UserDao userDao;

  @GetMapping("/")
  public String getUserList(Model model) {
    model.addAttribute("userModel", new User());
    model.addAttribute("users", userDao.getUserList());
    return "user";
  }

  @PostMapping("/save")
  public String saveUser(@ModelAttribute("userModel") User user) {
    userDao.add(user);
    return "redirect:/";
  }

}
