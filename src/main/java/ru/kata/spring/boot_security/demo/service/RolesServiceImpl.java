package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;

@Service
@Transactional
public class RolesServiceImpl implements RolesService {
    private final RoleRepository roleRepository;

    @Autowired
    public RolesServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public List<Role> roles() {
        return (List<Role>) roleRepository.findAll();
    }
}
