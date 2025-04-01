package com.week3.homework.dto;

import com.week3.homework.entities.ProfessorEntity;
import com.week3.homework.entities.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubjectDTO {

    private Long id;
    private String title;

    private ProfessorSummaryDTO professor;
    private List<StudentSummaryDTO> student;

}
