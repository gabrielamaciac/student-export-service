package com.learning.student.exportservice.service.impl;

import com.learning.student.exportservice.integration.model.Student;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class ProcessService {
    private final SpringTemplateEngine springTemplateEngine;

    public ProcessService(SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine = springTemplateEngine;
    }

    public String processStudent(Student student) {
        Context context = new Context();
        context.setVariable("student", student);
        return springTemplateEngine.process("student", context);
    }
}
