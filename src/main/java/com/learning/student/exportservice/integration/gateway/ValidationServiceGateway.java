package com.learning.student.exportservice.integration.gateway;

import com.learning.student.exportservice.integration.model.Student;
import com.learning.student.exportservice.integration.model.ValidationDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Calls validation-service to get the validation details for the student.
 */
@Slf4j
@Component
public class ValidationServiceGateway {

    private final RestTemplate restTemplate;

    @Value("${validation-service.url}")
    private String url;

    public ValidationServiceGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Retryable(value = {HttpClientErrorException.class}, maxAttempts = 4, backoff = @Backoff(1000))
    public List<ValidationDetail> validateStudent(Student student, String studentId) {
        HttpEntity<Student> request = new HttpEntity<>(student);
        ParameterizedTypeReference<List<ValidationDetail>> typeRef = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<ValidationDetail>> response =
                restTemplate.exchange(url + studentId, HttpMethod.POST, request, typeRef);
        log.info("Validation service responded with: " + response.getStatusCodeValue());
        return response.getBody();
    }
}
