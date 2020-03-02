package com.dev.cinema.dao;

import com.dev.cinema.model.Role;

/**
 * @author Sergey Klunniy
 */
public interface RoleDao {

    Role addRole(Role role);

    Role getRole(String roleName);
}
