package com.week3.homework.services;

import com.week3.homework.Exception.ResourceNotFoundException;
import com.week3.homework.configs.ModelMapperConfig;
import com.week3.homework.dto.ProfessorDTO;
import com.week3.homework.dto.StudentDTO;
import com.week3.homework.dto.StudentSummaryDTO;
import com.week3.homework.entities.ProfessorEntity;
import com.week3.homework.entities.SubjectEntity;
import com.week3.homework.repositories.ProfessorRepository;
import com.week3.homework.repositories.SubjectRepository;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ModelMapper modelMapper;
    private final SubjectService subjectService;
    private final SubjectRepository subjectRepository;
    public ProfessorService(ProfessorRepository professorRepository,ModelMapper modelMapper, SubjectRepository subjectRepository, SubjectService subjectService){
        this.professorRepository = professorRepository;
        this.modelMapper = modelMapper;
        this.subjectService = subjectService;
        this.subjectRepository = subjectRepository;
    }



    public void existProfessorById(Long professorId){
        boolean exists = professorRepository.existsById(professorId);
        if(!exists) throw new ResourceNotFoundException("Professor with id: "+professorId+" does not exists");
    }

    public ProfessorDTO getProfessorById(Long professorId) {
        existProfessorById(professorId);
        ProfessorEntity professorEntity = professorRepository.findById(professorId).get();

        ProfessorDTO professorDTO = modelMapper.map(professorEntity,ProfessorDTO.class);

        List<StudentSummaryDTO> studentDTO = professorEntity.getStudents().stream().
                map(student -> modelMapper.map(student,StudentSummaryDTO
                        .class)).collect(Collectors.toList());

        professorDTO.setStudent(studentDTO);
        return professorDTO;
    }

    public ProfessorDTO createNewProfessor(ProfessorDTO newProfessor){
        ProfessorEntity professorEntity = modelMapper.map(newProfessor,ProfessorEntity.class);
        ProfessorEntity professor = professorRepository.save(professorEntity);
        return modelMapper.map(professor,ProfessorDTO.class);
    }


    public ProfessorDTO AssignSubjectToProfessor(Long professorId, Long subjectId) {
        existProfessorById(professorId);
        subjectService.existSubjectById(subjectId);

        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).get();
        ProfessorEntity professorEntity = professorRepository.findById(professorId).get();

        if(!professorEntity.getSubjects().contains(subjectEntity)){
            professorEntity.getSubjects().add(subjectEntity);
        }

        subjectEntity.setProfessor(professorEntity);

        professorRepository.save(professorEntity);

        return modelMapper.map(professorEntity,ProfessorDTO.class);
    }
}
