package com.dev.cinema.service;

import com.dev.cinema.model.User;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public interface UserService {
    User add(User user);

    User findByEmail(String email);

    List<User> getUserList();

    User getUserById(Long id);
}
