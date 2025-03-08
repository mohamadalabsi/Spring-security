package org.me.learning.springsecurity1.service;

import org.me.learning.springsecurity1.model.Users;
import org.me.learning.springsecurity1.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    JWTService jwtService ;

    private BCryptPasswordEncoder encoder =new BCryptPasswordEncoder(12);

    public Users register (Users user ){
        user.setPassword(encoder.encode(user.getPassword()));
       return  userRepo.save(user);

    }

    public String verify(Users user) {
//    we will use Authentication manager
//  !       i have already Authentication provider i just have to call them , we are now using post method as login
       Authentication authentication=  manager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

       if (authentication.isAuthenticated()){
           return jwtService.generateToken();

       }else  return "fail";


     }

}
