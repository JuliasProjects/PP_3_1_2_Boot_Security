package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Users;
import ru.kata.spring.boot_security.demo.service.RolesService;
import ru.kata.spring.boot_security.demo.service.UsersService;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UsersController {

    private final UsersService userService;
    private final RolesService roleService;

    @Autowired
    public UsersController(@Qualifier("usersServiceImpl") UsersService userService, RolesService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping
    public String user(Model model, Principal principal) {
        long id = userService.getByNickName(principal.getName()).getId();
        model.addAttribute("user", userService.get(id));
        model.addAttribute("roles", roleService.roles());
        return "user/user";
    }

    @GetMapping("/edit")
    public String edit(Model model, Principal principal) {
        long id = userService.getByNickName(principal.getName()).getId();
        model.addAttribute("user", userService.get(id));
        model.addAttribute("roles", roleService.roles());
        return "user/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") Users user, Principal principal) {
        long id = userService.getByNickName(principal.getName()).getId();
        userService.update(id, user);
        return "redirect:/user";
    }

    @DeleteMapping
    public String delete(Principal principal) {
        long id = userService.getByNickName(principal.getName()).getId();
        userService.delete(id);
        return "redirect:/logout";
    }
}