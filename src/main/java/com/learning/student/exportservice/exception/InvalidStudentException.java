package com.learning.student.exportservice.exception;

public class InvalidStudentException extends RuntimeException {
    public InvalidStudentException(String message) {
        super(message);
    }
}
