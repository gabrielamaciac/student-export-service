package com.learning.student.exportservice.integration.gateway;

import com.learning.student.exportservice.integration.model.Student;
import com.learning.student.exportservice.integration.model.ValidationDetail;
import com.learning.student.exportservice.util.ExportTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test for {@link ValidationServiceGateway}
 */
class ValidationServiceGatewayTest {

    private RestTemplate restTemplate;
    private ValidationServiceGateway validationServiceGateway;

    private Student student = ExportTestData.getStudent();
    private ValidationDetail validationDetail = ExportTestData.getValidationDetail();

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        validationServiceGateway = new ValidationServiceGateway(restTemplate);
    }

    @Test
    void validateStudentReturnsValidListForInvalidStudent() {
        List<ValidationDetail> validationDetails = Collections.singletonList(validationDetail);
        when(restTemplate.exchange(any(String.class),
                any(HttpMethod.class), any(HttpEntity.class), any(ParameterizedTypeReference.class)))
                .thenReturn(new ResponseEntity<>(validationDetails, HttpStatus.OK));


        List<ValidationDetail> actualResponse = validationServiceGateway.validateStudent(student, ExportTestData.STUDENT_ID);

        assertFalse(actualResponse.isEmpty());
        assertEquals(validationDetail.getErrorName(), actualResponse.get(0).getErrorName());
        assertEquals(validationDetail.getErrorDescription(), actualResponse.get(0).getErrorDescription());
        assertEquals(validationDetail.getStudentId(), actualResponse.get(0).getStudentId());
    }

    @Test
    void validateStudentReturnsEmptyListForValidStudent() {
        List<ValidationDetail> validationDetails = Collections.emptyList();
        when(restTemplate.exchange(any(String.class),
                any(HttpMethod.class), any(HttpEntity.class), any(ParameterizedTypeReference.class)))
                .thenReturn(new ResponseEntity<>(validationDetails, HttpStatus.OK));


        List<ValidationDetail> actualResponse = validationServiceGateway.validateStudent(student, ExportTestData.STUDENT_ID);

        assertTrue(actualResponse.isEmpty());
    }
}
