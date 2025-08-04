package org.me.learning.springsecurity1.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

//    ? step 1  , the session id will not change even if u refresh the page
//    to get the session ID
    @GetMapping
    public String hello(HttpServletRequest request) {
        return "Hello World" + request.getSession().getId();

    }
}
