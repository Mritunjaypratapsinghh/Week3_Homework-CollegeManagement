package com.week3.homework.dto;

import com.week3.homework.entities.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdmissionRecordDTO {

    private Long id;
    private Integer fees;
    private Long studentId;

}
