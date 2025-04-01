package com.week3.homework.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(name = "Student_Professor",joinColumns = @JoinColumn(name = "professor_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<ProfessorEntity> professor;
    @ManyToMany
    @JoinTable(name = "Subjects Chosen", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "subjects_id"))
    private List<SubjectEntity> subjects;

    @OneToOne(mappedBy ="student")
    private AdmissionRecordEntity studentRecord;
}
