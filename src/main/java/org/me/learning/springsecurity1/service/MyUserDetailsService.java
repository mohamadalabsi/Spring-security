package org.me.learning.springsecurity1.service;

import org.me.learning.springsecurity1.model.UserPrincipal;
import org.me.learning.springsecurity1.model.Users;
import org.me.learning.springsecurity1.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        it returns the user that is why we assigned it with user
        Users user =userRepo.findByUsername(username);

        if (user ==null){
            throw new UsernameNotFoundException("user not found ");
        }
//        now we have to return UserDetails object but it is an Interface so we can not , we make a class that implements it
        return new UserPrincipal(user);
    }
}
