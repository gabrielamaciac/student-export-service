package com.learning.student.exportservice.facade;

import com.learning.student.exportservice.service.ExportService;
import com.learning.student.exportservice.util.ExportTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test for {@link ExportFacade}
 */
class ExportFacadeTest {
    private ExportService exportService;
    private ExportFacade exportFacade;

    @BeforeEach
    void setUp() {
        exportService = mock(ExportService.class);
        exportFacade = new ExportFacadeImpl(exportService);
    }

    @Test
    void exportStudentReturnsValidXmlString() {
        when(exportService.exportStudent(ExportTestData.STUDENT_ID)).thenReturn(ExportTestData.getStudentXML());

        String actualXml = exportFacade.exportStudent(ExportTestData.STUDENT_ID);

        assertEquals(ExportTestData.getStudentXML(), actualXml);
    }
}
