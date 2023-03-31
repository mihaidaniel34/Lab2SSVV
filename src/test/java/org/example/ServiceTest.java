package org.example;

import org.example.domain.Student;
import org.example.domain.Tema;
import org.example.repository.NotaXMLRepo;
import org.example.repository.StudentXMLRepo;
import org.example.repository.TemaXMLRepo;
import org.example.service.Service;
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
        service.deleteTema("69");
    }

    @Test
    public void testAddStudent() {
        int size = 0;
        var studenti = service.getAllStudenti();
        for (Student value : studenti) {
            size++;
        }
        assert (service.findStudent("69") == null);
        Student student = new Student("69", "nume", 936, "email", "prof");
        assert(service.addStudent(student) == null);
        assert (service.findStudent("69") != null);
        assert(service.addStudent(student) != null);
        int otherSize = 0;
        for (Student val : studenti) {
            otherSize++;
        }
        assert (otherSize == size + 1);
    }

    @Test
    public void testAddStudentAlreadyExists() {
        int size = 0;
        var students = service.getAllStudenti();
        for (Student value : students) {
            size++;
        }
        assert (service.findStudent("69") != null);
        Student student = new Student("69", "nume", 936, "email", "prof");
        assert (service.addStudent(student) != null);
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


    // testAddTema with valid Tema
    @Test
    public void testAddTemaValid() {
        int size = 0;
        var teme = service.getAllTeme();
        for (Tema value : teme) {
            size++;
        }
        assert (service.findTema("69") == null);
        Tema tema = new Tema("69", "descriere", 5, 6);
        service.addTema(tema);
        assert (service.findTema("69") != null);
        int otherSize = 0;
        for (Tema val : teme) {
            otherSize++;
        }
        assert (otherSize == size + 1);
    }

    //test addTema already exists
    @Test
    public void testAddTemaAlreadyExists() {
        int size = 0;
        var teme = service.getAllTeme();
        for (Tema value : teme) {
            size++;
        }
        assert (service.findTema("69") == null);
        Tema tema = new Tema("69", "descriere", 5, 6);
        service.addTema(tema);
        assert (service.findTema("69") != null);
        assert(service.addTema(tema) != null);
        int otherSize = 0;
        for (Tema val : teme) {
            otherSize++;
        }
        assert (otherSize == size + 1);
    }

    //test addTema with null id
    @Test
    public void testAddTemaNullId() {
        int size = 0;
        var teme = service.getAllTeme();
        for (Tema value : teme) {
            size++;
        }
        assert (service.findTema("69") == null);
        Tema tema = new Tema(null, "descriere", 5, 6);
        assertThrows(ValidationException.class, () -> service.addTema(tema));
        assert (service.findTema("69") == null);
        int otherSize = 0;
        for (Tema val : teme) {
            otherSize++;
        }
        assert (otherSize == size);
    }

    // test addTema with null description
    @Test
    public void testAddTemaNullDescription() {
        int size = 0;
        var teme = service.getAllTeme();
        for (Tema value : teme) {
            size++;
        }
        assert (service.findTema("69") == null);
        Tema tema = new Tema("69", null, 5, 6);
        assertThrows(ValidationException.class, () -> service.addTema(tema));
        assert (service.findTema("69") == null);
        int otherSize = 0;
        for (Tema val : teme) {
            otherSize++;
        }
        assert (otherSize == size);
    }

    // test addTema with deadline less than 1 or greater than 14
    @Test
    public void testAddTemaInvalidDeadline() {
        int size = 0;
        var teme = service.getAllTeme();
        for (Tema value : teme) {
            size++;
        }
        assert (service.findTema("69") == null);
        Tema tema = new Tema("69", "descriere", 0, 6);
        assertThrows(ValidationException.class, () -> service.addTema(tema));
        assert (service.findTema("69") == null);
        tema.setDeadline(15);
        assertThrows(ValidationException.class, () -> service.addTema(tema));
        assert (service.findTema("69") == null);
        int otherSize = 0;
        for (Tema val : teme) {
            otherSize++;
        }
        assert (otherSize == size);
    }

    // test addTema with primire less than 1 or greater than 14
    @Test
    public void testAddTemaInvalidPrimire() {
        int size = 0;
        var teme = service.getAllTeme();
        for (Tema value : teme) {
            size++;
        }
        assert (service.findTema("69") == null);
        Tema tema = new Tema("69", "descriere", 5, 0);
        assertThrows(ValidationException.class, () -> service.addTema(tema));
        assert (service.findTema("69") == null);
        tema.setPrimire(15);
        assertThrows(ValidationException.class, () -> service.addTema(tema));
        assert (service.findTema("69") == null);
        int otherSize = 0;
        for (Tema val : teme) {
            otherSize++;
        }
        assert (otherSize == size);
    }

    // test addTema with empty id
    @Test
    public void testAddTemaEmptyId() {
        int size = 0;
        var teme = service.getAllTeme();
        for (Tema value : teme) {
            size++;
        }
        assert (service.findTema("69") == null);
        Tema tema = new Tema("", "descriere", 5, 6);
        assertThrows(ValidationException.class, () -> service.addTema(tema));
        assert (service.findTema("69") == null);
        int otherSize = 0;
        for (Tema val : teme) {
            otherSize++;
        }
        assert (otherSize == size);
    }

    // test addTema with empty description
    @Test
    public void testAddTemaEmptyDescription() {
        int size = 0;
        var teme = service.getAllTeme();
        for (Tema value : teme) {
            size++;
        }
        assert (service.findTema("69") == null);
        Tema tema = new Tema("69", "", 5, 6);
        assertThrows(ValidationException.class, () -> service.addTema(tema));
        assert (service.findTema("69") == null);
        int otherSize = 0;
        for (Tema val : teme) {
            otherSize++;
        }
        assert (otherSize == size);
    }

}