package com.learning.student.exportservice.controller;

import com.learning.student.exportservice.controller.api.ExportController;
import com.learning.student.exportservice.facade.ExportFacade;
import com.learning.student.exportservice.util.ExportTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test for {@link ExportController}
 */
class ExportControllerTest {
    private ExportFacade exportFacade;
    private ExportController exportController;

    @BeforeEach
    void setUp() {
        exportFacade = mock(ExportFacade.class);
        exportController = new ExportController(exportFacade);
    }

    @Test
    void exportReturns200OkStatusCodeAndValidXmlBody() {
        when(exportFacade.exportStudent(ExportTestData.STUDENT_ID)).thenReturn(ExportTestData.getStudentXML());

        ResponseEntity<String> actualResponse = exportController.export(ExportTestData.STUDENT_ID);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(ExportTestData.getStudentXML(), actualResponse.getBody());
    }
}
