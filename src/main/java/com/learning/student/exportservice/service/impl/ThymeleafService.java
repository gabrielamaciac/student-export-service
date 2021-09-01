package com.learning.student.exportservice.service.impl;

import com.learning.student.exportservice.integration.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class ThymeleafService {

    public static final String PATH = "C:\\Users\\gabrielamaciac\\Documents\\GitHub\\student-export";

    @Autowired
    SpringTemplateEngine springTemplateEngine;

    public String export(Student student) {
        Context context = new Context();
        context.setVariable("student", student);
        String content = springTemplateEngine.process("student", context);
        writeToPath(content);
        return PATH;
    }

    private void writeToPath(String content) {
        try {
            File filePath = File.createTempFile("student_", ".xml", new File(PATH));
            PrintWriter writer = new PrintWriter(filePath, "UTF-8");
            writer.print(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
