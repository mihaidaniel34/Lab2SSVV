package org.example.service;

import junit.framework.TestCase;
import org.example.domain.Student;
import org.example.repository.StudentXMLRepo;
import org.example.validation.StudentValidator;
import org.junit.After;
import org.junit.Before;

public class ServiceTest extends TestCase {

    StudentXMLRepo studentXMLRepo;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        String filenameStudent = "fisiere/Studenti.xml";

        StudentValidator studentValidator = new StudentValidator();
        studentXMLRepo = new StudentXMLRepo(filenameStudent);

    }

    @After
    public void tearDown() throws Exception {
        studentXMLRepo.delete("69");
    }

    public void testAddStudent() {
        int size = 0;
        var students = studentXMLRepo.findAll();
        for (Student value : students) {
            size++;
        }
        assert (studentXMLRepo.findOne("69") == null);
        Student student = new Student("69", "ion", 936, "nam", "nare");
        assert (studentXMLRepo.save(student) == null);
        assert (studentXMLRepo.findOne("69") != null);
        int otherSize = 0;
        for (Student val : students) {
            otherSize++;
        }
        assert (otherSize - size == 1);
    }

    public void testAddStudentAlreadyExists() {
        int size = 0;
        var students = studentXMLRepo.findAll();

        Student student = new Student("69", "ion", 936, "nam", "nare");
        studentXMLRepo.save(student);
         for (Student value : students) {
            size++;
        }
        assert (studentXMLRepo.save(student) == student);
        int otherSize = 0;

        for (Student val : students) {
            otherSize++;
        }

        assert (otherSize == size);

    }
}