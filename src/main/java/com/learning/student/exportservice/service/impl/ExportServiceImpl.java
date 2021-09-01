package com.learning.student.exportservice.service.impl;

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

    ValidationServiceGateway validationServiceGateway;

    public ExportServiceImpl(ValidationServiceGateway validationServiceGateway) {
        this.validationServiceGateway = validationServiceGateway;
    }

    @Override
    public boolean validateStudent(Student student, String studentId) {
        List<ValidationDetail> validationDetails = validationServiceGateway.validateStudent(student, studentId);
        log.info("Validation details received: " + validationDetails.size());
        if (validationDetails.size() <= 0) {
            return true;
        }
        return false;
    }

    @Override
    public void exportStudent(Student student) {

    }
}
