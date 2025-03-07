package org.me.learning.springsecurity1.controller;


import org.me.learning.springsecurity1.model.Users;
import org.me.learning.springsecurity1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Users register (@RequestBody Users user){

        return userService.register(user);


    }
}
