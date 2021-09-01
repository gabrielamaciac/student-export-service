package com.learning.student.exportservice.controller.api;

import com.learning.student.exportservice.controller.model.StudentDto;
import com.learning.student.exportservice.facade.ExportFacade;
import com.learning.student.exportservice.integration.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/export")
@RestController
@Slf4j
public class ExportController {

    private final ExportFacade exportFacade;
    private final ModelMapper modelMapper = new ModelMapper();

    public ExportController(ExportFacade exportFacade) {
        this.exportFacade = exportFacade;
    }

    @PostMapping("/student/{studentId}")
    public ResponseEntity<String> export(@RequestBody StudentDto studentDto, @PathVariable String studentId) {
        try {
            String path = exportFacade.exportStudent(modelMapper.map(studentDto, Student.class), studentId);
            return new ResponseEntity<>("Student exported to path: " + path, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
