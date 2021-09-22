package com.learning.student.exportservice.util;

import com.learning.student.exportservice.integration.model.Address;
import com.learning.student.exportservice.integration.model.Grade;
import com.learning.student.exportservice.integration.model.Mark;
import com.learning.student.exportservice.integration.model.Student;
import com.learning.student.exportservice.integration.model.ValidationDetail;

import java.util.Collections;
import java.util.UUID;

public class ExportTestData {
    public static final String STUDENT_ID = "4ce2dddb-438a-4305-b7e3-9981fda59355";
    public static final String TEST_FIRST_NAME = "TestFirstName";
    public static final String TEST_LAST_NAME = "TestLastName";
    public static final String TEST_CNP = "TestCNP";
    public static final String DATE_OF_BIRTH = "1987-12-10";
    public static final String TEST_CITY = "TestCity";
    public static final String TEST_COUNTRY = "TestCountry";
    public static final String TEST_NUMBER = "TestNumber";
    public static final String TEST_STREET = "TestStreet";
    public static final String TEST_SUBJECT = "TestSubject";
    public static final String DATE_RECEIVED = "2020-03-12";

    public static final UUID STUDENT_UUID = UUID.fromString(STUDENT_ID);
    public static final String ERROR_NAME = "Some error name";
    public static final String ERROR_DESCRIPTION = "Some error description";

    public static Student getStudent() {
        Address address = new Address(TEST_CITY, TEST_COUNTRY, TEST_NUMBER, TEST_STREET);
        Mark mark = new Mark(DATE_RECEIVED, 10.0);
        Grade grade = new Grade(TEST_SUBJECT, Collections.singletonList(mark));
        return new Student(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_CNP, DATE_OF_BIRTH, address, Collections.singletonList(grade));
    }

    public static ValidationDetail getValidationDetail() {
        return new ValidationDetail(STUDENT_UUID, ERROR_NAME, ERROR_DESCRIPTION);
    }

    public static String getStudentXML() {
        return "<student>\n" +
                "    <firstName>TestFirstName</firstName>\n" +
                "    <lastName>TestLastName</lastName>\n" +
                "    <cnp>TestCnp</cnp>\n" +
                "    <dateOfBirth>1987-12-10</dateOfBirth>\n" +
                "    <address>\n" +
                "        <street>TestStreet</street>\n" +
                "        <number>TestNumber</number>\n" +
                "        <city>TestCity</city>\n" +
                "        <country>TestCountry</country>\n" +
                "    </address>\n" +
                "    <grades>\n" +
                "        <subject>TestSubject</subject>\n" +
                "        <marks>\n" +
                "            <dateReceived>2020-03-12</dateReceived>\n" +
                "            <mark>10</mark>\n" +
                "        </marks>\n" +
                "    </grades>\n" +
                "</student>";
    }

}
