package com.dev.cinema.service.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sergey Klunniy
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public User add(User user) {
        byte[] solt = HashUtil.getSalt();
        user.setSalt(solt);
        user.setPassword(HashUtil.hashPassword(user.getPassword(), solt));

        return userDao.add(user);
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    @Transactional
    public List<User> getUserList() {
        return userDao.getUserList();
    }

}
