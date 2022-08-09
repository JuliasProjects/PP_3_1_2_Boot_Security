package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.core.GrantedAuthority;
import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;

public interface RoleDao {
    List<Role> roles();
    void addRole(Role role);


}
