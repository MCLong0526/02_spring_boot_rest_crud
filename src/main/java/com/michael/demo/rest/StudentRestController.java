package com.michael.demo.rest;

import com.michael.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    @PostConstruct
    public void loadData(){
        theStudents= new ArrayList<>();
        theStudents.add(new Student("Michael", "Jordan"));
        theStudents.add(new Student("John", "Wick"));
        theStudents.add(new Student("Tony", "Stark"));
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId){
        if(studentId >= theStudents.size() || studentId < 0){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return theStudents.get(studentId);
    }

}
