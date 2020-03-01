package com.dev.cinema.controllers;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.dao.UserDao;
import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

/**
 * @author Sergey Klunniy
 */
@Controller
public class InitializeController {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void initialize() {
        roleDao.addRole(new Role("ADMIN"));
        roleDao.addRole(new Role("USER"));
        userDao.add(new User("ava_inet@mail.ru", passwordEncoder.encode("1234")));
        userDao.add(new User("book.best@mail.ru", passwordEncoder.encode("1234")));
    }
}
