package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.entities.Users;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UsersDaoImpl implements UsersDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Users user) {
        entityManager.persist(user);
    }

    @Override
    public List<Users> listUsers() {
        TypedQuery<Users> query = entityManager.createQuery("SELECT u FROM Users u", Users.class);
        return query.getResultList();
    }

    @Override
    public Users findByEmail(String email) {
        return entityManager.find(Users.class, email);
    }

    @Override
    public void update(long id) {
        entityManager.merge(id);
    }

    @Override
    public void delete(String email) {
        Users user = findByEmail(email);
        if (user != null) {
            entityManager.remove(user);
        }
    }
}
