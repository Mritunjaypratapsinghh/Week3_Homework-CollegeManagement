package com.week3.homework.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class ProfessorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "professor")
    @JsonIgnore
    private List<SubjectEntity> subjects;
    @ManyToMany
    @JoinTable(name = "Students_Under_Professor",joinColumns = @JoinColumn(name = "professor_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<StudentEntity> students;



}
