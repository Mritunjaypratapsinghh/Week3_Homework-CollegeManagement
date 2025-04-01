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

    @PatchMapping("/{studentId}/subject/{subjectId}")
    public StudentDTO AssignSubjectToStudent(@PathVariable Long studentId, @PathVariable Long subjectId){
        return studentService.AssignSubjectToStudent(studentId,subjectId);
    }

    @PatchMapping("/{studentId}/professor/{professorId}")
    public StudentDTO AssignProfessorToStudent(@PathVariable Long studentId, @PathVariable Long professorId){
        return studentService.AssignProfessorToStudent(studentId,professorId);
    }


}

