package com.dev.cinema.dao;

import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;

/**
 * @author Sergey Klunniy
 */
public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);

    void clear(ShoppingCart shoppingCart);
}
