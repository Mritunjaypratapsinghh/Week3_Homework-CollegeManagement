package com.week3.homework.controllers;


import com.week3.homework.dto.ProfessorDTO;
import com.week3.homework.services.ProfessorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService){
        this.professorService = professorService;
    }

    @GetMapping(path = "/{professorId}")
    public ProfessorDTO getProfessorById(@PathVariable Long professorId){
        return professorService.getProfessorById(professorId);
    }

    @PostMapping
    public ProfessorDTO createNewProfessor(@RequestBody ProfessorDTO newProfessor){
        return professorService.createNewProfessor(newProfessor);
    }

    @PatchMapping(path = "/{professorId}/subject/{subjectId}")
    public ProfessorDTO AssignSubjectToProfessor(@PathVariable Long professorId, @PathVariable Long subjectId){
        return professorService.AssignSubjectToProfessor(professorId,subjectId);
    }


}
