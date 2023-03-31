package org.example.service;

import org.example.domain.Student;
import org.example.repository.NotaXMLRepo;
import org.example.repository.StudentXMLRepo;
import org.example.repository.TemaXMLRepo;
import org.example.validation.NotaValidator;
import org.example.validation.StudentValidator;
import org.example.validation.TemaValidator;
import org.example.validation.ValidationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class ServiceTest {

    StudentXMLRepo studentXMLRepo;
    Service service;

    @Before
    public void setUp() throws Exception {
        String filenameStudent = "fisiere/Studenti.xml";

        StudentValidator studentValidator = new StudentValidator();
        studentXMLRepo = new StudentXMLRepo(filenameStudent);
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        TemaValidator temaValidator = new TemaValidator();
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @After
    public void tearDown() throws Exception {
        studentXMLRepo.delete("69");
    }

    @Test
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

    @Test
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

    //test addStudent with null id
    @Test
    public void testAddStudentNullId() {
        int size = 0;
        var students = service.getAllStudenti();
        for (Student value : students) {
            size++;
        }
        assert (service.findStudent("69") == null);
        Student student = new Student(null, "ion", 936, "nam", "nare");
        assertThrows(ValidationException.class, () -> service.addStudent(student));
        assert (service.findStudent("69") == null);
        int otherSize = 0;
        for (Student val : students) {
            otherSize++;
        }
        assert (otherSize == size);
    }

    //test addStudent with null name
    @Test
    public void testAddStudentNullName() {
        int size = 0;
        var students = service.getAllStudenti();
        for (Student value : students) {
            size++;
        }
        assert (service.findStudent("69") == null);
        Student student = new Student("69", null, 936, "nam", "nare");
        assertThrows(ValidationException.class, () -> service.addStudent(student));
        assert (service.findStudent("69") == null);
        int otherSize = 0;
        for (Student val : students) {
            otherSize++;
        }
        assert (otherSize == size);
    }
    //test addStudent with group negative
    @Test
    public void testAddStudentGroupNegative() {
        int size = 0;
        var students = service.getAllStudenti();
        for (Student value : students) {
            size++;
        }
        assert (service.findStudent("69") == null);
        Student student = new Student("69", "ion", -936, "nam", "nare");
        assertThrows(ValidationException.class, () -> service.addStudent(student));
        assert (service.findStudent("69") == null);
        int otherSize = 0;
        for (Student val : students) {
            otherSize++;
        }
        assert (otherSize == size);
    }
    //test addStudent with null email
    @Test
    public void testAddStudentNullEmail() {
        int size = 0;
        var students = service.getAllStudenti();
        for (Student value : students) {
            size++;
        }
        assert (service.findStudent("69") == null);
        Student student = new Student("69", "ion", 936, null, "nare");
        assertThrows(ValidationException.class, () -> service.addStudent(student));
        assert (service.findStudent("69") == null);
        int otherSize = 0;
        for (Student val : students) {
            otherSize++;
        }
        assert (otherSize == size);
    }
    //test addStudent with null teacher
    @Test
    public void testAddStudentNullTeacher() {
        int size = 0;
        var students = service.getAllStudenti();
        for (Student value : students) {
            size++;
        }
        assert (service.findStudent("69") == null);
        Student student = new Student("69", "ion", 936, "nam", null);
        assertThrows(ValidationException.class, () -> service.addStudent(student));
        assert (service.findStudent("69") == null);
        int otherSize = 0;
        for (Student val : students) {
            otherSize++;
        }
        assert (otherSize == size);
    }
    //test addStudent with empty id
    @Test
    public void testAddStudentEmptyId() {
        int size = 0;
        var students = service.getAllStudenti();
        for (Student value : students) {
            size++;
        }
        assert (service.findStudent("69") == null);
        Student student = new Student("", "ion", 936, "nam", "nare");
        assertThrows(ValidationException.class, () -> service.addStudent(student));
        assert (service.findStudent("69") == null);
        int otherSize = 0;
        for (Student val : students) {
            otherSize++;
        }
        assert (otherSize == size);
    }
    //test addStudent with empty name
    @Test
    public void testAddStudentEmptyName() {
        int size = 0;
        var students = service.getAllStudenti();
        for (Student value : students) {
            size++;
        }
        assert (service.findStudent("69") == null);
        Student student = new Student("69", "", 936, "nam", "nare");
        assertThrows(ValidationException.class, () -> service.addStudent(student));
        assert (service.findStudent("69") == null);
        int otherSize = 0;
        for (Student val : students) {
            otherSize++;
        }
        assert (otherSize == size);
    }
    //test addStudent with empty email
    @Test
    public void testAddStudentEmptyEmail() {
        int size = 0;
        var students = service.getAllStudenti();
        for (Student value : students) {
            size++;
        }
        assert (service.findStudent("69") == null);
        Student student = new Student("69", "ion", 936, "", "nare");
        assertThrows(ValidationException.class, () -> service.addStudent(student));
        assert (service.findStudent("69") == null);
        int otherSize = 0;
        for (Student val : students) {
            otherSize++;
        }
        assert (otherSize == size);
    }
    //test addStudent with empty teacher
    @Test
    public void testAddStudentEmptyTeacher() {
        int size = 0;
        var students = service.getAllStudenti();
        for (Student value : students) {
            size++;
        }
        assert (service.findStudent("69") == null);
        Student student = new Student("69", "ion", 936, "nam", "");
        assertThrows(ValidationException.class, () -> service.addStudent(student));
        assert (service.findStudent("69") == null);
        int otherSize = 0;
        for (Student val : students) {
            otherSize++;
        }
        assert (otherSize == size);
    }


}