package org.me.learning.springsecurity1.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // spring will know this is a configuration file
@EnableWebSecurity // i do not want to use the default spring security Configuration so i have to use this annotation and implement it here
public class SecurityConfig {

//    we have to use beans and return them
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.build();
//        we are saying to spring here do not go to the default use this , and the security is not working now 
    }
}
