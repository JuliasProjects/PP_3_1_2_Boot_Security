package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UsersDao;
import ru.kata.spring.boot_security.demo.entities.Users;


import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {


    private final UsersDao usersDao;

    @Autowired
    public UsersServiceImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @NotNull
    @Transactional
    @Override
    public void add(Users user) {
        usersDao.add(user);
    }

    @NotNull
    @Transactional(readOnly = true)
    @Override
    public List<Users> listUsers() {
        return usersDao.listUsers();
    }

    @NotNull
    @Transactional
    @Override
    public Users get(Long id) {
        return usersDao.findById(id);
    }


    @NotNull
    @Transactional(readOnly = true)
    @Override
    public void update(long id) {
        usersDao.update(id);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        usersDao.delete(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersDao.findById(username);
        if(user==null){
            throw  new UsernameNotFoundException(String.format("User %s not found", username));
        }
        return new Users(user.getEmail(), user.getPassword(), user.getAuthorities());
    }
}