package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.Users;
import ru.kata.spring.boot_security.demo.service.RolesService;
import ru.kata.spring.boot_security.demo.service.UsersService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public String getAllUsers(Model model, Principal principal) {
        Users user = (Users) userService.getUserByEmail(principal.getName());
        model.addAttribute("userAuthorized", user);
        model.addAttribute("listUsers", userService.listUsers());
        model.addAttribute("newUser", new Users());
        List<Role> listRoles = rolesService.roles();
        model.addAttribute("listRoles", listRoles);
        return "admin/mainAdminPage";
    }

    @GetMapping("/{id}") //shows user by given id //correct
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.get(id));
        return "admin/user";
    }


    @GetMapping("/new")// adds new user //correct
    public String newUser(@ModelAttribute("user") Users user, Model model) {
        model.addAttribute("roles", rolesService.roles());
        return "admin/new";
    }

    @PostMapping() //create new user //correct
    public String create(@ModelAttribute("user") Users user, @RequestParam("idRoles") List<Long> idRoles) {
        Set<Role> roleList = new HashSet<>();
        for(Long id:idRoles) {
            roleList.add(rolesService.findRoleById(id));
        }
        user.setRoles((List<Role>) roleList);
        userService.add(user);
        return "redirect:/mainAdminPage";
    }

    @GetMapping("/{id}/edit") //edit user by his id //correct
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.get(id));
        model.addAttribute("roles", rolesService.roles());
        return "admin/edit";
    }

    @PatchMapping("/{id}/edit") //
    public String updateUser(@ModelAttribute("user")Users user, @PathVariable("id") Long id,
                             @RequestParam("idRoles") List<Long> rolesId) {
        Set<Role> listRoles = new HashSet<>();
        for (Long idRole : rolesId) {
            listRoles.add(rolesService.findRoleById(idRole));
        }
        user.setRoles((List<Role>) listRoles);
        System.out.println(user);
        userService.update(id);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/{id}") // correct
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/mainAdminPage";
    }
}
