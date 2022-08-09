package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;

public class RolesServiceImpl implements RolesService {
    private RoleDao roleDao;

    @Autowired
    public RolesServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> roles() {
        return roleDao.roles();
    }

    @Override
    public void addRole(Role role) {
    roleDao.addRole(role);
    }
}
