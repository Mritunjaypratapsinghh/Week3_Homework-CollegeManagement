package com.week3.homework.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToMany(mappedBy = "students")
    @JsonIgnore
    private List<ProfessorEntity> professor;
    @ManyToMany
    @JoinTable(name = "Subjects Chosen", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "subjects_id"))
    private List<SubjectEntity> subjects;

    @OneToOne(mappedBy ="student")
    private AdmissionRecordEntity studentRecord;
}
