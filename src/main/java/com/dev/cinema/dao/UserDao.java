package com.dev.cinema.dao;

import com.dev.cinema.model.User;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
public interface UserDao {
    User add(User user);

    User findByEmail(String email);

    List<User> getUserList();
}
