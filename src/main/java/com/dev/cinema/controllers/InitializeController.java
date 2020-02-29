package com.dev.cinema.controllers;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.dao.UserDao;
import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

/**
 * @author Sergey Klunniy
 */
@Controller
public class InitializeController {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private UserDao userDao;

    @PostConstruct
    private void initialize() {
        roleDao.addRole(new Role("ADMIN"));
        roleDao.addRole(new Role("USER"));
        userDao.add(new User("ava_inet@mail.ru", "1234"));
        userDao.add(new User("book.best@mail.ru", "1234"));
    }
}
