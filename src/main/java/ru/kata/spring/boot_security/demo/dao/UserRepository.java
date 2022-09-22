package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {
    Users findByEmail(String email);

}