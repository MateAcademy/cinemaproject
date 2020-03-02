package com.dev.cinema.controllers;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.dao.UserDao;
import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;

import java.util.HashSet;
import java.util.Set;
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
        Role admin = new Role("ADMIN");
        Role user = new Role("USER");
        roleDao.addRole(admin);
        roleDao.addRole(user);
        Set<Role> rolesUserAdmin = new HashSet<>();
        rolesUserAdmin.add(admin);

        Set<Role> rolesUserRoot = new HashSet<>();
        rolesUserRoot.add(user);
        userDao.add(new User("ava_inet@mail.ru", passwordEncoder.encode("1234"), rolesUserAdmin));
        userDao.add(new User("book.best@mail.ru", passwordEncoder.encode("1234"), rolesUserRoot));
    }
}
