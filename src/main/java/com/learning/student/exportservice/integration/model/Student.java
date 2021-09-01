package com.learning.student.exportservice.integration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {
    private String firstName;
    private String lastName;
    private String cnp;
    private String dateOfBirth;
    private Address address;
    private List<Grade> grades;
}
