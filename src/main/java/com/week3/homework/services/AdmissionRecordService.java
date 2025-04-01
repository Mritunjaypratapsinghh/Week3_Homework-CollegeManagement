package com.week3.homework.services;


import com.week3.homework.Exception.ResourceNotFoundException;
import com.week3.homework.dto.AdmissionRecordDTO;
import com.week3.homework.entities.AdmissionRecordEntity;
import com.week3.homework.entities.StudentEntity;
import com.week3.homework.repositories.AdmissionRecordRepository;
import com.week3.homework.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AdmissionRecordService {

    private final AdmissionRecordRepository admissionRecordRepository;
    private final ModelMapper modelMapper;
    private final StudentService studentService;
    private final StudentRepository studentRepository;

    public AdmissionRecordService(AdmissionRecordRepository admissionRecordRepository,ModelMapper modelMapper,StudentService studentService, StudentRepository studentRepository){
        this.admissionRecordRepository = admissionRecordRepository;
        this.studentService=studentService;
        this.modelMapper = modelMapper;
        this.studentRepository = studentRepository;
    }



    public void existAdmissionRecordById(Long admissionRecordId){
        boolean exists = admissionRecordRepository.existsById(admissionRecordId);
        if(!exists) throw new ResourceNotFoundException("AdmissionRecord with id: "+admissionRecordId+" does not exists");
    }

    public AdmissionRecordDTO getAdmissionRecordById(Long admissionRecordId) {
        existAdmissionRecordById(admissionRecordId);
        AdmissionRecordEntity admissionRecordEntity = admissionRecordRepository.findById(admissionRecordId).get();
        return modelMapper.map(admissionRecordEntity,AdmissionRecordDTO.class);
    }

    public AdmissionRecordDTO createNewAdmissionRecord(AdmissionRecordDTO newAdmissionRecord){
        Long studentId = newAdmissionRecord.getStudentId();
        studentService.isExistsStudentById(studentId);
        AdmissionRecordEntity admissionRecordEntity = modelMapper.map(newAdmissionRecord,AdmissionRecordEntity.class);
        AdmissionRecordEntity admissionRecord = admissionRecordRepository.save(admissionRecordEntity);
        return modelMapper.map(admissionRecord,AdmissionRecordDTO.class);
    }

}
