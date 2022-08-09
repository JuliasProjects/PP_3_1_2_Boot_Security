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
@RequestMapping("/user")
public class UsersController {

    private final UsersService userService;

    @Autowired
    public UsersController(UsersService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}") //shows user by given id //correct
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.get(id));
        return "user/user";
    }

    @GetMapping("/{id}/edit") //edit user by his id //correct
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.get(id));
        return "user/edit";
    }

    @PatchMapping("/{id}/edit") //
    public String update(@ModelAttribute("user") @Valid Users user, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "user/edit";
        }
        userService.update(id);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}") // correct
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/user";
    }
}