package com.week3.homework.dto;

import com.week3.homework.entities.StudentEntity;
import com.week3.homework.entities.SubjectEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfessorDTO {


    private Long id;
    private String name;
    private List<SubjectSummaryDTO> subjects;
    private List<StudentSummaryDTO> student;
}
