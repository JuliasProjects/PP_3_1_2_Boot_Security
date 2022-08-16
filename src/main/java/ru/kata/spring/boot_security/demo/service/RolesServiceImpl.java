package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;

@Service
@Transactional
public class RolesServiceImpl implements RolesService {
    private final RoleDao roleDao;

    @Autowired
    public RolesServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> roles() {
        return roleDao.roles();
    }

    @Override
    @Transactional
    public void addRole(Role role) {
    roleDao.addRole(role);
    }

    @Override
    public Role findRoleById(long id) {
        return roleDao.findRoleById(id);
    }
}
