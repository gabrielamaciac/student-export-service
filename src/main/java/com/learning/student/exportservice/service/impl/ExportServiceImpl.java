package com.learning.student.exportservice.service.impl;

import com.learning.student.exportservice.exception.InvalidStudentException;
import com.learning.student.exportservice.integration.gateway.StudentServiceGateway;
import com.learning.student.exportservice.integration.gateway.ValidationServiceGateway;
import com.learning.student.exportservice.integration.model.Student;
import com.learning.student.exportservice.integration.model.ValidationDetail;
import com.learning.student.exportservice.service.ExportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@Slf4j
public class ExportServiceImpl implements ExportService {

    private final StudentServiceGateway studentServiceGateway;
    private final ValidationServiceGateway validationServiceGateway;
    private final SpringTemplateEngine springTemplateEngine;

    @Value("${file.pathName}")
    private String path;

    public ExportServiceImpl(StudentServiceGateway studentServiceGateway, ValidationServiceGateway validationServiceGateway,
                             SpringTemplateEngine springTemplateEngine) {
        this.studentServiceGateway = studentServiceGateway;
        this.validationServiceGateway = validationServiceGateway;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    public String exportStudent(String studentId) {
        Student student = getStudentById(studentId);
        boolean isValid = validateStudent(student, studentId);
        log.info("Student is valid: " + isValid);
        if (isValid) {
            return export(student);
        } else {
            throw new InvalidStudentException("Student is invalid");
        }
    }

    private Student getStudentById(String studentId) {
        Student student = studentServiceGateway.getStudentById(studentId);
        if (!Objects.isNull(student)) {
            return student;
        } else {
            throw new NoSuchElementException("No student found with the given id.");
        }
    }

    private boolean validateStudent(Student student, String studentId) {
        List<ValidationDetail> validationDetails = validationServiceGateway.validateStudent(student, studentId);
        log.info("Validation details received: " + validationDetails.size());
        return validationDetails.isEmpty();
    }

    private String export(Student student) {
        Context context = new Context();
        context.setVariable("student", student);
        String content = springTemplateEngine.process("student", context);
        log.info("Student was written to path: " + path);
        return content;
    }
}
