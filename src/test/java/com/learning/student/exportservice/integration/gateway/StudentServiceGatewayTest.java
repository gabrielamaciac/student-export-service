package com.learning.student.exportservice.integration.gateway;

import com.learning.student.exportservice.integration.model.Student;
import com.learning.student.exportservice.util.ExportTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test for {@link StudentServiceGateway}
 */
class StudentServiceGatewayTest {
    private RestTemplate restTemplate;
    private StudentServiceGateway studentServiceGateway;

    private Student student = ExportTestData.getStudent();

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        studentServiceGateway = new StudentServiceGateway(restTemplate);
    }

    @Test
    void getStudentByIdReturnsValidStudent() {
        when(restTemplate.getForEntity(any(String.class), any(Class.class))).thenReturn(new ResponseEntity<>(student, HttpStatus.OK));

        Student actualResponse = studentServiceGateway.getStudentById(ExportTestData.STUDENT_ID);

        assertEquals(student.getFirstName(), actualResponse.getFirstName());
        assertEquals(student.getCnp(), actualResponse.getCnp());
    }

    @Test
    void getStudentByIdThrowsException() {
        when(restTemplate.getForEntity(any(String.class), any(Class.class))).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        assertThrows(NoSuchElementException.class, () -> studentServiceGateway.getStudentById(ExportTestData.STUDENT_ID));
    }
}
