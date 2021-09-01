package com.learning.student.exportservice.service;

import com.learning.student.exportservice.integration.model.Student;

public interface ExportService {
    boolean validateStudent(Student student, String studentId);

    void exportStudent(Student student);
}
