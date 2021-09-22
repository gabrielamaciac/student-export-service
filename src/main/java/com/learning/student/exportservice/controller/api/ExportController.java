package com.learning.student.exportservice.controller.api;

import com.learning.student.exportservice.facade.ExportFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Export controller used to export a student from DB in XML format.
 */
@RestController
@Slf4j
public class ExportController implements ExportApi {

    private final ExportFacade exportFacade;

    public ExportController(ExportFacade exportFacade) {
        this.exportFacade = exportFacade;
    }

    @Override
    public ResponseEntity<String> export(String studentId) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_XML);
        String xmlContent = exportFacade.exportStudent(studentId);
        return new ResponseEntity<>(xmlContent, responseHeaders, HttpStatus.OK);
    }
}
