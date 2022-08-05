package ru.kata.spring.boot_security.demo.dao;
import ru.kata.spring.boot_security.demo.entities.Users;

import java.util.List;

public interface UsersDao {
    void add(Users user);

    List<Users> listUsers();

    Users findByEmail(String email);

    void update(long id);

    void delete(String email);
}