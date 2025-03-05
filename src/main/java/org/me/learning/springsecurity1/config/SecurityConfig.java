package org.me.learning.springsecurity1.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // spring will know this is a configuration file
@EnableWebSecurity // i do not want to use the default spring security Configuration so i have to use this annotation and implement it here
public class SecurityConfig {

    @Autowired
    private  UserDetailsService userDetailsService;

//    we have to use beans and return them
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(customizer ->customizer.disable()); // to disable the csrf token
        httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated()); // no one should access the page without authentication
//        httpSecurity.formLogin(Customizer.withDefaults()); // enabling the formLogin , if i want to do with postman i will get literally a form login
        httpSecurity.httpBasic(Customizer.withDefaults());
//        we already took about different ways to handel the csrf Token , the other way is to make Http session stateless , it will be updated after refreshing
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        we have to disable the formLogin code up there , we will get a popup , if we dont we will get the form everytime even if i logged in
//        we using lambda now , there is another way but this is much easier , and because Customizer is FunctionalInterface and not the default one we can use lambda with it


        return httpSecurity.build();
//        we are saying to spring here do not go to the default use this , and the security is not working now
//         and now we have to secure it , implement that layer , i will do it before return statement




    }

    // !       now these just for testing and we are not using it

//    @Bean
//    public UserDetailsService userDetailsService (){
//
//        UserDetails user1 = User.withDefaultPasswordEncoder()
//                .username("mo.alabsi")
//                .password("mohamad101010")
//                .roles("user")
//                .build();
//        UserDetails user2 = User.withDefaultPasswordEncoder()
//                .username("aabbssii")
//                .password("mohamad101010.")
//                .roles("user")
//                .build();
////         u can not create an object from an Interface (UserDetailsService) in this case and in general so we have to find a class that implement this interface
//        return new InMemoryUserDetailsManager(user1,user2);
//    }

    //        ! we want to use a DB and use username and password like a real user


    public AuthenticationProvider authenticationProvider(){
//        the same problem as above with interface
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); // without password encoder , just for testing
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
