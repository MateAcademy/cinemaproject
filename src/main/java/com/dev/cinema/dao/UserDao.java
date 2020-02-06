package com.dev.cinema.dao;

import com.dev.cinema.model.User;

/**
 * @author Sergey Klunniy
 */
public interface UserDao {
    User add(User user);

    User findByEmail(String email);

}
