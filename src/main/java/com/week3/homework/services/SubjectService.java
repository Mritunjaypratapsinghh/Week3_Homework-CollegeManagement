package com.week3.homework.services;

import com.week3.homework.Exception.ResourceNotFoundException;
import com.week3.homework.dto.StudentSummaryDTO;
import com.week3.homework.dto.SubjectDTO;
import com.week3.homework.entities.SubjectEntity;
import com.week3.homework.repositories.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final ModelMapper modelMapper;

    public SubjectService(SubjectRepository subjectRepository,ModelMapper modelMapper){
        this.subjectRepository = subjectRepository;
        this.modelMapper = modelMapper;
    }



    public void existSubjectById(Long subjectId){
        boolean exists = subjectRepository.existsById(subjectId);
        if(!exists) throw new ResourceNotFoundException("Subject with id: "+subjectId+" does not exists");
    }

    public SubjectDTO getSubjectById(Long subjectId) {
        existSubjectById(subjectId);
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).get();
        SubjectDTO subjectDTO = modelMapper.map(subjectEntity,SubjectDTO.class);
        List<StudentSummaryDTO> studentSummaryDTOS = subjectEntity.getStudents().stream().
                map(studentEntity -> modelMapper.map(studentEntity,StudentSummaryDTO.class)).collect(Collectors.toList());

        subjectDTO.setStudent(studentSummaryDTOS);
        return subjectDTO;
    }

    public SubjectDTO createNewSubject(SubjectDTO newSubject){
        SubjectEntity subjectEntity = modelMapper.map(newSubject,SubjectEntity.class);
        SubjectEntity subject = subjectRepository.save(subjectEntity);
        return modelMapper.map(subject,SubjectDTO.class);
    }


}
