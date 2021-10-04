package com.learning.student.exportservice.integration.gateway;

import com.learning.student.exportservice.integration.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Calls the student-service to retrieve the student for export.
 */
@Slf4j
@Component
public class StudentServiceGateway {
    private final RestTemplate restTemplate;

    @Value("${student-service.url}")
    private String url;

    public StudentServiceGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Retryable(value = {HttpClientErrorException.class}, maxAttempts = 4, backoff = @Backoff(1000))
    public Student getStudentById(String studentId) {
        String getByIdUrl = url + studentId;
        ResponseEntity<Student> response = restTemplate.getForEntity(getByIdUrl, Student.class);
        log.info("Student service responded with: " + response.getStatusCodeValue());
        return response.getBody();
    }
}
