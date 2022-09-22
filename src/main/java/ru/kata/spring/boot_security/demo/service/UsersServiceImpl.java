package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserRepository;
import ru.kata.spring.boot_security.demo.entities.Users;

import java.util.List;


@Service
@Transactional
public class UsersServiceImpl implements UsersService {
    private final UserRepository userRepository;

    @Autowired
    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Users add(Users user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Users> listUsers() {
        return (List<Users>) userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Users get(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional(readOnly = true)
    public void update(long id, Users updatedUser) {
        Users user = userRepository.findById(id).get();
        updatedUser.setPassword(updatedUser.getPassword().isEmpty() ? user.getPassword() : new BCryptPasswordEncoder().encode(updatedUser.getPassword()));
        userRepository.save(user);

    }

    @Override
    @Transactional
    public void delete(long id) {
        Users user = userRepository.findById(id).get();
        userRepository.delete(user);

    }

    @Override
    @Transactional(readOnly = true)
    public Users getByNickName(String name) {
        return userRepository.findByEmail(name);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());
    }
}