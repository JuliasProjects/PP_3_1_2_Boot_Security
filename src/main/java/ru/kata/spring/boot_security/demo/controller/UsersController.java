package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Users;
import ru.kata.spring.boot_security.demo.service.UsersService;


import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {


    @Autowired
    private final UsersService userService;

    public UsersController(UsersService userService) {
        this.userService = userService;
    }


    @GetMapping() //list of users //correct
    public String index(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "/index";
    }

    @GetMapping("/{id}") //shows user by given id //correct
    public String show(@PathVariable("email") String email, Model model) {
        model.addAttribute("user", userService.get(email));
        return "/user";
    }

    @GetMapping("/new")// adds new user //correct
    public String newUser(@ModelAttribute("user") Users user) {
        return "/new";
    }

    @PostMapping() //create new user //correct
    public String create(@ModelAttribute("user") Users user) {
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit") //edit user by his id //correct
    public String edit(Model model, @PathVariable("email") String email) {
        model.addAttribute("user", userService.get(email));
        return "/edit";
    }

    @PatchMapping("/{id}/edit") //
    public String update(@ModelAttribute("user") @Valid Users user, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors()){
            return "/edit";
        }
        userService.update(id);
        return  "redirect:/users";
    }

    @DeleteMapping("/{id}") // correct
    public String delete(@PathVariable("email") String email) {
        userService.delete(email);
        return "redirect:/users";
    }
}