package com.learning.student.exportservice.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface ExportApi {
    @PostMapping("/student/{studentId}")
    ResponseEntity<String> export(@PathVariable String studentId);
}
