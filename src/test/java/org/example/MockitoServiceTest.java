package org.example;

import org.example.domain.Nota;
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
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.Assert.assertThrows;

public class MockitoServiceTest {

    @Mock
    private StudentXMLRepo studentXMLRepo;

    @Mock
    private TemaXMLRepo temaXMLRepo;

    @Mock
    private NotaXMLRepo notaXMLRepo;


    private Service service;


    @Before
    public void setUp() throws Exception {
        studentXMLRepo = Mockito.mock(StudentXMLRepo.class);
        temaXMLRepo = Mockito.mock(TemaXMLRepo.class);
        notaXMLRepo = Mockito.mock(NotaXMLRepo.class);
        StudentValidator studentValidator = new StudentValidator();

        NotaValidator notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
        TemaValidator temaValidator = new TemaValidator();
        service = new Service(studentXMLRepo, studentValidator, temaXMLRepo, temaValidator, notaXMLRepo, notaValidator);

    }

    // test case for addStudent using the mock
    @Test
    public void testAddStudent() {
        Student student = new Student("1", "name", 936, "email", "teacher");
        Mockito.when(studentXMLRepo.save(student)).thenReturn(null);
        assert (service.addStudent(student) == null);
        Mockito.verify(studentXMLRepo, Mockito.times(1)).save(student);
        Mockito.when(studentXMLRepo.findOne("1")).thenReturn(student);
        assert(service.findStudent("1") == student);
        Mockito.when(studentXMLRepo.save(student)).thenReturn(student);
        assert (service.addStudent(student) == student);

    }

    // integration test for addAssignment (addStudent + addAssignment)
    @Test
    public void testAddAssignment() {
        Tema tema = new Tema("1", "desc", 14, 1);
        Mockito.when(temaXMLRepo.save(tema)).thenReturn(null);
        assert (service.addTema(tema) == null);
        Mockito.verify(temaXMLRepo, Mockito.times(1)).save(tema);
        Mockito.when(temaXMLRepo.findOne("1")).thenReturn(tema);
        assert(service.findTema("1") == tema);
        Mockito.when(temaXMLRepo.save(tema)).thenReturn(tema);
        assert (service.addTema(tema) == tema);
    }
    // test addGrade with mock
    @Test
    public void testAddGrade() {
        Student student = new Student("1", "name", 936, "email", "teacher");
        Mockito.when(studentXMLRepo.findOne("1")).thenReturn(student);
        Tema tema = new Tema("1", "desc", 14, 1);
        Mockito.when(temaXMLRepo.findOne("1")).thenReturn(tema);
        Nota nota = new Nota("1", "1", "1", 10, LocalDate.now());
        Mockito.when(notaXMLRepo.save(nota)).thenReturn(null);
        assert (service.addNota(nota, "good") == null);
        Mockito.verify(notaXMLRepo, Mockito.times(1)).save(nota);
        Mockito.when(notaXMLRepo.findOne("1")).thenReturn(nota);
        assert(service.findNota("1") == nota);
        Mockito.when(notaXMLRepo.save(nota)).thenReturn(nota);
        assert (service.addNota(nota, "good") == nota.getNota());
    }


    // integration test for addGrade (addStudent + addAssignment + addGrade)
    @Test
    public void testAddGradeIntegration() {
        Student student = new Student("1", "name", 936, "email", "teacher");
        Tema tema = new Tema("1", "desc", 14, 1);
        assertThrows(ValidationException.class, () -> service.addNota(new Nota("1", "1", "1", 10, LocalDate.now()), "good"));
        Mockito.when(studentXMLRepo.findOne("1")).thenReturn(student);
        assertThrows(ValidationException.class, () -> service.addNota(new Nota("1", "1", "1", 10, LocalDate.now()), "good"));
        Mockito.when(temaXMLRepo.findOne("1")).thenReturn(tema);
        assert (service.addNota(new Nota("1", "1", "1", 10, LocalDate.now()), "good") == null);
    }



}
