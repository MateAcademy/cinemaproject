package com.dev.cinema.service.impl;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.model.Role;
import com.dev.cinema.service.RoleService;

/**
 * @author Sergey Klunniy
 */
public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role add(Role role) {
        return roleDao.addRole(role);
    }

    @Override
    public Role getByRoleName(String roleName) {
        return roleDao.getRole(roleName);
    }
}
