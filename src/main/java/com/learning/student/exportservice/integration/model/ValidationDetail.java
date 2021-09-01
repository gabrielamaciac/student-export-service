package com.learning.student.exportservice.integration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValidationDetail {
    private UUID studentId;
    private String errorName;
    private String errorDescription;
}
