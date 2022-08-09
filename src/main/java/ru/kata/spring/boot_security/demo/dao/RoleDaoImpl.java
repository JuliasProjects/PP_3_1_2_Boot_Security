package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entities.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Role> roles() {
        TypedQuery<Role> typedQuery = entityManager.createQuery("SELECT u FROM Role u", Role.class);
        return typedQuery.getResultList();
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }
}
