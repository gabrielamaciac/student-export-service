package com.learning.student.exportservice.service;

import com.learning.student.exportservice.integration.model.Student;

public interface ExportService {
    String exportStudent(Student student, String studentId);
}
