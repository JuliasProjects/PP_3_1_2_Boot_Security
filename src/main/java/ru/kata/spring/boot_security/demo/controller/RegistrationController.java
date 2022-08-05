package ru.kata.spring.boot_security.demo.controller;

import org.h2.engine.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.entities.Users;
import ru.kata.spring.boot_security.demo.service.UsersService;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(String email, Map<String, Object> model){
        Users user = usersService.get(email);
        if (user != null ){
            model.put("message", "This user already exists");
            return "registration";
        }
        user.setRoles(Collections.singleton(Role.USER));
        usersService.add(user);
        return "redirect:/login";
    }
}
