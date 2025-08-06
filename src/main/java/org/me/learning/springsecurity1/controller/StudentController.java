package org.me.learning.springsecurity1.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.me.learning.springsecurity1.model.Student;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
//? step 2 csrf token



@RestController
public class StudentController {

//    we do not have a database rn so i will do this
    private List <Student> students =new ArrayList<>(List.of(
            new Student(1,"mohammad",90),
            new Student(2,"malek",80)

));
//!     because we use spring security we have to put the username and password for every Api

  @GetMapping("/student")
    public List <Student> getStudents(){
      return students;
    }
//!    now here where CSRF Token take place we do not use it for get , there is better alternative to this we will discuss it later , it is in  SecurityConfig class
//!    we have to pass a Key and a Value , we can get it from the browser or pass it in the code here , by default it is enabled , and we use it for these APIs

//     one of the first things we had to deal with , lets say i have a session id and using a website and then i downloaded a crack version of something , they want to get the session id
//     and if they can get it then they can access the website i am using as me and steal stuff , so here csrf token takes place and prevent that , this is the first way
//     second way we can make out session stateless so it will change always and we do not have to worry about the session id
@GetMapping("/csrf-token")
public CsrfToken  getToken(HttpServletRequest request){
    return (CsrfToken) request.getAttribute("_csrf");  //getAttribute is type Object , so we have to typecasting so it can be as CsrfToken , i will come back for this later
} // it will return a token , we use it in the header section in post , put and delete mapping (methods)

    @PostMapping ("/student")
    public Student addStudent(@RequestBody Student student){
      students.add(student);
      return student;
    }


}
