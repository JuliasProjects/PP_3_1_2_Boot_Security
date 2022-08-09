package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;

public interface RolesService {
    List<Role> roles();
    void addRole(Role role);
}
