package com.learning.student.exportservice.facade;

import com.learning.student.exportservice.service.impl.ExportServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ExportFacadeImpl implements ExportFacade {
    private final ExportServiceImpl exportService;

    public ExportFacadeImpl(ExportServiceImpl exportService) {
        this.exportService = exportService;
    }

    @Override
    public String exportStudent(String studentId) {
        return exportService.exportStudent(studentId);
    }
}
