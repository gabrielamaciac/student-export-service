package com.learning.student.exportservice.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GradeDto {
    //(discipline, date of grade, grade)
    private String subject;
    private List<MarkDto> marks;
}
