package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.Users;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
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
    public Users findById(Long id) {
        return entityManager.find(Users.class, id);
    }

    @Override
    public void update(long id) {
        entityManager.merge(id);
    }

    @Override
    public void delete(Long id) {
        Users user = findById(id);
        if (user != null) {
            entityManager.remove(user);
        } else {
            System.out.println("No user was found");
        }
    }

    @Override
    public Users getUserByEmail(String email) {
        return entityManager.createQuery("SELECT u FROM Users u WHERE u.email = '"+ email +"'", Users.class).getSingleResult();
    }
}
