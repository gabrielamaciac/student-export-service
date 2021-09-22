package com.learning.student.exportservice.service.impl;

import com.learning.student.exportservice.exception.InvalidStudentException;
import com.learning.student.exportservice.integration.gateway.StudentServiceGateway;
import com.learning.student.exportservice.integration.gateway.ValidationServiceGateway;
import com.learning.student.exportservice.integration.model.Student;
import com.learning.student.exportservice.integration.model.ValidationDetail;
import com.learning.student.exportservice.service.ExportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ExportServiceImpl implements ExportService {

    private final StudentServiceGateway studentServiceGateway;
    private final ValidationServiceGateway validationServiceGateway;
    private final ProcessService processService;

    public ExportServiceImpl(StudentServiceGateway studentServiceGateway, ValidationServiceGateway validationServiceGateway,
                             ProcessService processService) {
        this.studentServiceGateway = studentServiceGateway;
        this.validationServiceGateway = validationServiceGateway;
        this.processService = processService;
    }

    @Override
    public String exportStudent(String studentId) {
        Student student = studentServiceGateway.getStudentById(studentId);
        boolean isValid = validateStudent(student, studentId);
        log.info("Student is valid: " + isValid);
        if (isValid) {
            return processService.processStudent(student);
        } else {
            throw new InvalidStudentException("Student is invalid");
        }
    }

    private boolean validateStudent(Student student, String studentId) {
        List<ValidationDetail> validationDetails = validationServiceGateway.validateStudent(student, studentId);
        log.info("Validation details received: " + validationDetails.size());
        return validationDetails.isEmpty();
    }
}
