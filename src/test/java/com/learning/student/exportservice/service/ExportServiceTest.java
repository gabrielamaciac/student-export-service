package com.learning.student.exportservice.service;

import com.learning.student.exportservice.exception.InvalidStudentException;
import com.learning.student.exportservice.integration.gateway.StudentServiceGateway;
import com.learning.student.exportservice.integration.gateway.ValidationServiceGateway;
import com.learning.student.exportservice.integration.model.Student;
import com.learning.student.exportservice.integration.model.ValidationDetail;
import com.learning.student.exportservice.service.impl.ExportServiceImpl;
import com.learning.student.exportservice.service.impl.ProcessService;
import com.learning.student.exportservice.util.ExportTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExportServiceTest {

    private StudentServiceGateway studentServiceGateway;
    private ValidationServiceGateway validationServiceGateway;
    private ProcessService processService;
    private ExportService exportService;

    private Student student = ExportTestData.getStudent();
    private ValidationDetail validationDetail = ExportTestData.getValidationDetail();

    @BeforeEach
    void setUp() {
        studentServiceGateway = mock(StudentServiceGateway.class);
        validationServiceGateway = mock(ValidationServiceGateway.class);
        processService = mock(ProcessService.class);
        exportService = new ExportServiceImpl(studentServiceGateway, validationServiceGateway, processService);
    }

    @Test
    void exportStudentReturnsCorrectXMLForValidStudent() {
        // Given
        List<ValidationDetail> validationDetails = Collections.emptyList();
        when(studentServiceGateway.getStudentById(ExportTestData.STUDENT_ID)).thenReturn(student);
        when(validationServiceGateway.validateStudent(student, ExportTestData.STUDENT_ID)).thenReturn(validationDetails);
        when(processService.processStudent(student)).thenReturn(ExportTestData.getStudentXML());

        // When
        String actualXml = exportService.exportStudent(ExportTestData.STUDENT_ID);

        // Then
        assertEquals(actualXml, ExportTestData.getStudentXML());
    }

    @Test
    void exportStudentThrowsExceptionForInvalidStudent() {
        // Given
        List<ValidationDetail> validationDetails = Collections.singletonList(validationDetail);
        when(studentServiceGateway.getStudentById(ExportTestData.STUDENT_ID)).thenReturn(student);
        when(validationServiceGateway.validateStudent(student, ExportTestData.STUDENT_ID)).thenReturn(validationDetails);

        // When Then
        assertThrows(InvalidStudentException.class, () -> exportService.exportStudent(ExportTestData.STUDENT_ID));
    }
}
