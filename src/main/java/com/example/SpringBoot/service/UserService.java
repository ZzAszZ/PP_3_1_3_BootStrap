package com.example.SpringBoot.service;

import com.example.SpringBoot.model.Role;
import com.example.SpringBoot.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;

public interface UserService {
    User userByid(long id);
    List<User> usersAll();
    User userAdd(User user);
    void userDelete(long id);
    public User findByUsername(String username);
    Set<Role> getRoles(String[] roles);
}
