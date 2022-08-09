package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Users;
import ru.kata.spring.boot_security.demo.service.RolesService;
import ru.kata.spring.boot_security.demo.service.UsersService;
import javax.validation.Valid;

@Controller
@RequestMapping("/mainAdminPage")
public class AdminController {
    private final UsersService userService;
    private final RolesService rolesService;

    @Autowired
    public AdminController(UsersService userService, RolesService rolesService) {
        this.userService = userService;
        this.rolesService = rolesService;
    }


    @GetMapping() //list of users //correct
    public String index(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "mainAdminPage";
    }

    @GetMapping("/{id}") //shows user by given id //correct
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.get(id));
        return "/user";
    }


    @GetMapping("/new")// adds new user //correct
    public String newUser(@ModelAttribute("user") Users user) {
        return "/new";
    }

    @PostMapping() //create new user //correct
    public String create(@ModelAttribute("user") Users user) {
        userService.add(user);
        return "redirect:/index";
    }

    @GetMapping("/{id}/edit") //edit user by his id //correct
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.get(id));
        return "/edit";
    }

    @PatchMapping("/{id}/edit") //
    public String update(@ModelAttribute("user") @Valid Users user, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors()){
            return "/edit";
        }
        userService.update(id);
        return  "redirect:/index";
    }

    @DeleteMapping("/{id}") // correct
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/index";
    }
}
