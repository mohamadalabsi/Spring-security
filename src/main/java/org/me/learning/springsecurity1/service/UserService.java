package org.me.learning.springsecurity1.service;

import org.me.learning.springsecurity1.model.Users;
import org.me.learning.springsecurity1.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder encoder =new BCryptPasswordEncoder(12);

    public Users register (Users user ){
        user.setPassword(encoder.encode(user.getPassword()));
       return  userRepo.save(user);

    }
}
