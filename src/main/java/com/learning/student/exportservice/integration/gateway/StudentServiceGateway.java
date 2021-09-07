package com.learning.student.exportservice.integration.gateway;

import com.learning.student.exportservice.integration.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.NoSuchElementException;

/**
 * Calls the student-service to retrieve the student for export.
 */
@Slf4j
@Component
public class StudentServiceGateway {
    RestTemplate restTemplate = new RestTemplate();

    @Value("${student-service.url}")
    private String url;

    public Student getStudentById(String studentId) {
        String getByIdUrl = url + studentId;
        try {
            ResponseEntity<Student> response = restTemplate.getForEntity(getByIdUrl, Student.class);
            log.info("Student service responded with: " + response.getStatusCodeValue());
            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new NoSuchElementException("No student found with the given id.");
        }
    }
}
