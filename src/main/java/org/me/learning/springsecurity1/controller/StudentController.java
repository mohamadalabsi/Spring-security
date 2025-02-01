package org.me.learning.springsecurity1.controller;


import org.me.learning.springsecurity1.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

//    we do not have a database rn so i will do this
    private List <Student> students =new ArrayList<>(List.of(
            new Student(1,"mohammad",90),
            new Student(2,"malek",80)

));

  @GetMapping("/students")
    public List <Student> getStudents(){
      return students;
    }


}
