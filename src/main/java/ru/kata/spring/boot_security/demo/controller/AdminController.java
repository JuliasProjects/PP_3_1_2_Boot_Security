package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Users;
import ru.kata.spring.boot_security.demo.service.RolesService;
import ru.kata.spring.boot_security.demo.service.UsersService;


@RestController
@RequestMapping("/api")
public class AdminController {
    private final UsersService userService;
    private final RolesService roleService;

    @Autowired
    public AdminController(@Qualifier("usersServiceImpl") UsersService userService, RolesService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping
    public String admin(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "admin/admin";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.get(id));
        return "admin/user";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") Users user, Model model) {
        model.addAttribute("roles", roleService.roles());
        return "admin/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") Users user) {
        long id = userService.add(user).getId();
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.get(id));
        model.addAttribute("roles", roleService.roles());
        return "admin/edit";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") Users user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/admin/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
