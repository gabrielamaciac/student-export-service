package com.learning.student.exportservice.facade;

import com.learning.student.exportservice.integration.model.Student;

public interface ExportFacade {
    String exportStudent(Student student, String studentId);
}
