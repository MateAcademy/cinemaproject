package com.dev.cinema.service;

import com.dev.cinema.model.User;

/**
 * @author Sergey Klunniy
 */
public interface UserService {
    User add(User user);

    User findByEmail(String email);
}
