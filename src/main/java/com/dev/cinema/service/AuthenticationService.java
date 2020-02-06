package com.dev.cinema.service;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.User;

/**
 * @author Sergey Klunniy
 */
public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
