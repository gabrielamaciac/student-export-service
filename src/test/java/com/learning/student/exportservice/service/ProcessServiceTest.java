package com.learning.student.exportservice.service;

import com.learning.student.exportservice.integration.model.Student;
import com.learning.student.exportservice.service.impl.ProcessService;
import com.learning.student.exportservice.util.ExportTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test for {@link ProcessService}
 */
class ProcessServiceTest {
    private SpringTemplateEngine springTemplateEngine;
    private ProcessService processService;

    @BeforeEach
    void setUp() {
        springTemplateEngine = mock(SpringTemplateEngine.class);
        processService = new ProcessService(springTemplateEngine);
    }

    @Test
    void processStudentReturnsCorrectXml() {
        // Given
        Student student = ExportTestData.getStudent();
        when(springTemplateEngine.process(any(String.class), any(Context.class))).thenReturn(ExportTestData.getStudentXML());

        // When
        String actualXml = processService.processStudent(student);

        //Then
        assertEquals(ExportTestData.getStudentXML(), actualXml);
    }
}
