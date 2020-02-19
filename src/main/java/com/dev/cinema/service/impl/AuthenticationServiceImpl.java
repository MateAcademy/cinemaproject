package com.dev.cinema.service.impl;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Sergey Klunniy
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public User login(String email, String password) throws AuthenticationException {
        User user = userService.findByEmail(email);
        if (user != null) {
            if (user.getPassword().equals(HashUtil.hashPassword(password, user.getSalt()))) {
                return user;
            }
        }
        throw new AuthenticationException("login or password incorrect");
    }

    @Override
    @Transactional
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        User userFromDb = userService.add(user);
        shoppingCartService.registerNewShoppingCart(userFromDb);
        return userFromDb;
    }
}
