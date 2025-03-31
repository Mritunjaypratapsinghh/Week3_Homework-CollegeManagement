package com.week3.homework.controllers;


import com.week3.homework.dto.StudentDTO;
import com.week3.homework.services.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }


    @GetMapping(path = "/{studentId}")
    public StudentDTO getStudentById(@PathVariable Long studentId){
        return studentService.getStudentById(studentId);
    }

    @PostMapping
    public StudentDTO createNewStudent(@RequestBody StudentDTO newStudent){
        return studentService.createNewStudent(newStudent);
    }


}

