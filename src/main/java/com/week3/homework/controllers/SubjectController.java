package com.week3.homework.controllers;

import com.week3.homework.dto.SubjectDTO;
import com.week3.homework.services.SubjectService;
import com.week3.homework.services.SubjectService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping(path = "/subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping(path = "/{subjectId}")
    public SubjectDTO getSubjectById(@PathVariable Long subjectId) {
        return subjectService.getSubjectById(subjectId);
    }

    @PostMapping
    public SubjectDTO createNewSubject(@RequestBody SubjectDTO newSubject) {
        return subjectService.createNewSubject(newSubject);
    }
}