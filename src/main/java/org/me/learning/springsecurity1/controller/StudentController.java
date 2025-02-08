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



// some notes
// i have to finish spring security then microservices with that i will be finishing 70% form spring framework , all is explained in the ipad (form)
@RestController
public class StudentController {

//    we do not have a database rn so i will do this
    private List <Student> students =new ArrayList<>(List.of(
            new Student(1,"mohammad",90),
            new Student(2,"malek",80)

));
//     because we use spring security we have to put the username and password for every Api

  @GetMapping("/student")
    public List <Student> getStudents(){
      return students;
    }
//    now here where CSRF Token take place we do not use it for get , there is better alternative to this we will discuss it later
//    we have to pass a Key and a Value , we can get it from the browser or pass it in the code here
@GetMapping("/csrf-token")
public CsrfToken  getToken(HttpServletRequest request){
    return (CsrfToken) request.getAttribute("_csrf");  //getAttribute is type Object , so we have to typecasting so it can be as CsrfToken , i will come back for this later
}

    @PostMapping ("/student")
    public Student addStudent(@RequestBody Student student){
      students.add(student);
      return student;
    }
//     before stating with Spring Security Configuration , i have to explain everything in my ipad as usual  (the last subject)

}
