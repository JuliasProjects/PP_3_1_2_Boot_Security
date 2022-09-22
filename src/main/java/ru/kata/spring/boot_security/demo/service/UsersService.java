package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entities.Users;

import java.util.List;

@Service
public interface UsersService extends UserDetailsService {
    Users add(Users user);

    List<Users> listUsers();

    Users get(long id);

    void update(long id, Users updatedUser);

    void delete(long id);

    Users getByNickName(String name);
}