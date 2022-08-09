package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.entities.Users;

import java.util.List;

public interface UsersService extends UserDetailsService {
    void add(Users user);

    List<Users> listUsers();

    Users get(Long id);

    void update(long id);

    void delete(Long id);

    Users getUserByEmail(String email);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}