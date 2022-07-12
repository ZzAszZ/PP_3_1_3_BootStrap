package com.example.SpringBoot.controller;

import com.example.SpringBoot.model.User;
import com.example.SpringBoot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/user")
    public String userInfo(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user-info";
    }

    @GetMapping("/admin/{id}")
    public String userById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.userByid(id));
        return "user-info";
    }


    @GetMapping("/admin-info")
    public String adminInfo(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "admin-info";
    }

    @GetMapping("/admin")
    public String findAll(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("users", userService.usersAll());
        return "admin-panel";
    }


    @PostMapping("/admin/user-create")
    public String createUser(User user, @RequestParam(value = "role") String[] roles){
        user.setRoles(userService.getRoles(roles));
        userService.userAdd(user);
        return "redirect:/admin";
    }


    @GetMapping("/admin/user-deleteconfirm/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.userDelete(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model){
        User user = userService.userByid(id);
        model.addAttribute("user", user);
        model.addAttribute("users", userService.usersAll());
        return "user-delete";
    }


    @GetMapping("/admin/user-update/{id}")
    public String updateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.userByid(id);
        model.addAttribute("user", user);
        model.addAttribute("users", userService.usersAll());
        return "user-edit";
    }

    @PostMapping("/admin/user-update")
    public String updateUser(User user, @RequestParam(value = "role") String[] roles){
        user.setRoles(userService.getRoles(roles));
        userService.userAdd(user);
        return "redirect:/admin";
    }
}