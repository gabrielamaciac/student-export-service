package com.learning.student.exportservice.integration.gateway;

import com.learning.student.exportservice.integration.model.Student;
import com.learning.student.exportservice.integration.model.ValidationDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    RestTemplate restTemplate = new RestTemplate();

    @Value("${validation-service.url}")
    private String url;

    public List<ValidationDetail> validateStudent(Student student, String studentId) {
        HttpEntity<Student> request = new HttpEntity<>(student);
        String validateByIdUrl = url + studentId;
        final ParameterizedTypeReference<List<ValidationDetail>> typeRef = new ParameterizedTypeReference<>() {
        };
        ResponseEntity<List<ValidationDetail>> response =
                restTemplate.exchange(validateByIdUrl, HttpMethod.POST, request, typeRef);
        log.info("Validation service responded with: " + response.getStatusCodeValue());
        return response.getBody();
    }
}
