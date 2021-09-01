package com.learning.student.exportservice.service.impl;

import com.learning.student.exportservice.integration.gateway.ValidationServiceGateway;
import com.learning.student.exportservice.integration.model.Student;
import com.learning.student.exportservice.integration.model.ValidationDetail;
import com.learning.student.exportservice.service.ExportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
@Slf4j
public class ExportServiceImpl implements ExportService {

    private final ValidationServiceGateway validationServiceGateway;
    private final SpringTemplateEngine springTemplateEngine;

    @Value("${file.pathName}")
    private String path;

    public ExportServiceImpl(ValidationServiceGateway validationServiceGateway, SpringTemplateEngine springTemplateEngine) {
        this.validationServiceGateway = validationServiceGateway;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    public String exportStudent(Student student, String studentId) {
        boolean isValid = validateStudent(student, studentId);
        log.info("Student is valid: " + isValid);
        if (isValid) {
            return export(student);
        } else {
            throw new RuntimeException("Student is invalid");
        }
    }

    private boolean validateStudent(Student student, String studentId) {
        List<ValidationDetail> validationDetails = validationServiceGateway.validateStudent(student, studentId);
        log.info("Validation details received: " + validationDetails.size());
        if (validationDetails.size() <= 0) {
            return true;
        }
        return false;
    }

    private String export(Student student) {
        Context context = new Context();
        context.setVariable("student", student);
        String content = springTemplateEngine.process("student", context);
        writeToPath(content);
        log.info("Student was written to path: " + path);
        return path;
    }

    private void writeToPath(String content) {
        try {
            File filePath = File.createTempFile("student_", ".xml", new File(path));
            PrintWriter writer = new PrintWriter(filePath, "UTF-8");
            writer.print(content);
            writer.close();
        } catch (IOException e) {
            log.error("Exception creating the file to specified path: " + e);
        }
    }
}
