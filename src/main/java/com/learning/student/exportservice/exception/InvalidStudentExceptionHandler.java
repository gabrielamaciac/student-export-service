package com.learning.student.exportservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class InvalidStudentExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidStudentException.class)
    protected ResponseEntity<Object> handleConflict(final InvalidStudentException ex, final WebRequest request) {
        log.error("InvalidStudentExceptionHandler caught exception: ", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}