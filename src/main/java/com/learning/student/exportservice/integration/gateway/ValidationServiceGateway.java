package com.learning.student.exportservice.integration.gateway;

import com.learning.student.exportservice.integration.model.Student;
import com.learning.student.exportservice.integration.model.ValidationDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
public class ValidationServiceGateway {

    public static final String BASE_URL = "http://localhost:8082";
    public static final String VALIDATE_STUDENT_URL = "/api/validation/student/";

    RestTemplate restTemplate = new RestTemplate();

    public List<ValidationDetail> validateStudent(Student student, String studentId) {
        HttpEntity<Student> request = new HttpEntity<>(student);
        String url = BASE_URL + VALIDATE_STUDENT_URL + studentId;
        final ParameterizedTypeReference<List<ValidationDetail>> typeRef = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<ValidationDetail>> response =
                restTemplate.exchange(url, HttpMethod.POST, request, typeRef);
        log.info("Validation service responded with: " + response.getStatusCodeValue());
        return response.getBody();
    }
}
