package com.week3.homework.dto;

import com.week3.homework.entities.ProfessorEntity;
import com.week3.homework.entities.SubjectEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {

    private Long id;
    private String name;
    private List<ProfessorEntity> professor;
    private List<SubjectEntity> subject;
}
