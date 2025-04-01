package com.week3.homework.services;

import com.week3.homework.Exception.ResourceNotFoundException;
import com.week3.homework.configs.ModelMapperConfig;
import com.week3.homework.dto.ProfessorDTO;
import com.week3.homework.dto.ProfessorSummaryDTO;
import com.week3.homework.dto.StudentDTO;
import com.week3.homework.dto.SubjectDTO;
import com.week3.homework.entities.ProfessorEntity;
import com.week3.homework.entities.StudentEntity;
import com.week3.homework.entities.SubjectEntity;
import com.week3.homework.repositories.ProfessorRepository;
import com.week3.homework.repositories.StudentRepository;
import com.week3.homework.repositories.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final SubjectService subjectService;
    private final SubjectRepository subjectRepository;
    private final ProfessorService professorService;
    private final ProfessorRepository professorRepository;

    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper, SubjectService subjectService, SubjectRepository subjectRepository, ProfessorRepository professorRepository, ProfessorService professorService) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
        this.subjectService = subjectService;
        this.subjectRepository = subjectRepository;
        this.professorService = professorService;
        this.professorRepository = professorRepository;

    }


    public void isExistsStudentById(Long studentId){
        boolean exists = studentRepository.existsById(studentId);
        if(!exists) throw new ResourceNotFoundException("Student does not exist with id: "+studentId);
    }

    public StudentDTO getStudentById(Long studentId) {
        isExistsStudentById(studentId);
        StudentEntity studentEntity = studentRepository.findById(studentId).get();
        StudentDTO studentDTO = modelMapper.map(studentEntity,StudentDTO.class);
        List<SubjectDTO> subjectDTOs = studentEntity.getSubjects().stream()
                .map(subject -> modelMapper.map(subject, SubjectDTO.class))
                .collect(Collectors.toList());
        studentDTO.setSubject(subjectDTOs);
        return studentDTO;

    }


    public StudentDTO createNewStudent(StudentDTO newStudent) {
        StudentEntity studentEntity = modelMapper.map(newStudent,StudentEntity.class);
        StudentEntity student = studentRepository.save(studentEntity);
        return modelMapper.map(student,StudentDTO.class);
    }


    public StudentDTO AssignSubjectToStudent(Long studentId, Long subjectId) {
        isExistsStudentById(studentId);
        StudentEntity studentEntity = studentRepository.findById(studentId).get();
        subjectService.existSubjectById(subjectId);
        SubjectEntity subjectEntity = subjectRepository.findById(subjectId).get();
        if (studentEntity.getSubjects() == null) {
            studentEntity.setSubjects(new ArrayList<>());
        }
        if(subjectEntity.getStudents()==null){
            subjectEntity.setStudents(new ArrayList<>());
        }
        if(!subjectEntity.getStudents().contains(studentEntity)){
            subjectEntity.getStudents().add(studentEntity);
        }

        if(!studentEntity.getSubjects().contains(subjectEntity)){
            studentEntity.getSubjects().add(subjectEntity);
        }
        studentRepository.save(studentEntity);
        StudentDTO studentDTO = modelMapper.map(studentEntity, StudentDTO.class);

        List<SubjectDTO> subjectDTOs = studentEntity.getSubjects().stream()
                .map(subject -> modelMapper.map(subject, SubjectDTO.class))
                .collect(Collectors.toList());

        studentDTO.setSubject(subjectDTOs);
        return studentDTO;
    }

    public StudentDTO AssignProfessorToStudent(Long studentId, Long professorId) {
        isExistsStudentById(studentId);
        professorService.existProfessorById(professorId);
        StudentEntity studentEntity = studentRepository.findById(studentId).get();
        ProfessorEntity professorEntity = professorRepository.findById(professorId).get();
        if (!studentEntity.getProfessor().contains(professorEntity)) {
            studentEntity.getProfessor().add(professorEntity);
        }

        if (!professorEntity.getStudents().contains(studentEntity)) {
            professorEntity.getStudents().add(studentEntity);
        }

        studentRepository.save(studentEntity);
        professorRepository.save(professorEntity);

        StudentDTO studentDTO = modelMapper.map(studentEntity, StudentDTO.class);

        List<ProfessorSummaryDTO> professorDTO = studentEntity.getProfessor().stream()
                .map(professor ->modelMapper.map(professor, ProfessorSummaryDTO.class)).toList();

        List<SubjectDTO> subjectDTOs = studentEntity.getSubjects().stream()
                .map(subject -> modelMapper.map(subject, SubjectDTO.class))
                .collect(Collectors.toList());

        studentDTO.setProfessor(professorDTO);
        studentDTO.setSubject(subjectDTOs);

        return studentDTO;

    }

}


