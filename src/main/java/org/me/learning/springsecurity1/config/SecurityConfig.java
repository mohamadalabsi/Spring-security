package org.me.learning.springsecurity1.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // spring will know this is a configuration file
@EnableWebSecurity // i do not want to use the default spring security Configuration so i have to use this annotation and implement it here
public class SecurityConfig {

//    we have to use beans and return them
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(customizer ->customizer.disable()); // to disable the csrf token
        httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated()); // no one should access the page without authentication
//        httpSecurity.formLogin(Customizer.withDefaults()); // enabling the formLogin , if i want to do with postman i will get literally a form login
        httpSecurity.httpBasic(Customizer.withDefaults());
//        we already taked about different ways to handel the csrf Token , the other way is to make Http session stateless
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        we have to disable the formLogin code up there , we will get a popup 

        return httpSecurity.build();
//        we are saying to spring here do not go to the default use this , and the security is not working now
//         and now we have to secure it , implement that layer , i will do it before return statement

    }
}
