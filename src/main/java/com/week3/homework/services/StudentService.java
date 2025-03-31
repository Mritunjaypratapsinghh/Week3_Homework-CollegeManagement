package com.week3.homework.services;

import com.week3.homework.Exception.ResourceNotFoundException;
import com.week3.homework.configs.ModelMapperConfig;
import com.week3.homework.dto.StudentDTO;
import com.week3.homework.entities.StudentEntity;
import com.week3.homework.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }


    public void isExistsStudentById(Long studentId){
        boolean exists = studentRepository.existsById(studentId);
        if(!exists) throw new ResourceNotFoundException("Student does not exist with id: "+studentId);
    }

    public StudentDTO getStudentById(Long studentId) {
        isExistsStudentById(studentId);
        StudentEntity studentEntity = studentRepository.findById(studentId).get();
        return modelMapper.map(studentEntity,StudentDTO.class);

    }


    public StudentDTO createNewStudent(StudentDTO newStudent) {
        StudentEntity studentEntity = modelMapper.map(newStudent,StudentEntity.class);
        StudentEntity student = studentRepository.save(studentEntity);
        return modelMapper.map(student,StudentDTO.class);
    }


}
