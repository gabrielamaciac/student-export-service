package com.learning.student.exportservice.facade;

import com.learning.student.exportservice.integration.model.Student;
import com.learning.student.exportservice.service.impl.ExportServiceImpl;
import com.learning.student.exportservice.service.impl.ThymeleafService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExportFacadeImpl implements ExportFacade {
    private final ExportServiceImpl exportService;
    private final ThymeleafService thymeleafService;

    public ExportFacadeImpl(ExportServiceImpl exportService, ThymeleafService thymeleafService) {
        this.exportService = exportService;
        this.thymeleafService = thymeleafService;
    }

    @Override
    public String exportStudent(Student student, String studentId) {
        boolean isValid = exportService.validateStudent(student, studentId);
        log.info("Student is valid: " + isValid);
        if (isValid) {
            String path = thymeleafService.export(student);
            return "Student exported to path: " + path;
        } else {
            throw new RuntimeException("Student is invalid");
        }
    }
}
