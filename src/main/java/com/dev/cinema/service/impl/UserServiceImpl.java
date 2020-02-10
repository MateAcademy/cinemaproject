package com.dev.cinema.service.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;

/**
 * @author Sergey Klunniy
 */
@Service
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        byte[] solt = HashUtil.getSalt();
        user.setSalt(solt);
        user.setPassword(HashUtil.hashPassword(user.getPassword(), solt));

        return userDao.add(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
