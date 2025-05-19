package it.bitify.libreria.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import it.bitify.libreria.exception.EntityNotFoundException;
import it.bitify.libreria.model.entity.Student;
import it.bitify.libreria.repository.StudentRepo;
import it.bitify.libreria.service.serviceImpl.StudentServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepo studentRepo;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    public void testGetStudentById_Found() {
        // Arrange
        Long id = 1L;
        Student student = new Student();
        student.seId(id);
        when(studentRepo.findById(id)).thenReturn(Optional.of(student));
        // Act
        Student result = studentService.getStudentById(id);
        // Assert
        assertEquals(student, result);
    }

    @Test
    public void testGetStudentById_NotFound() {
        // Arrange
        Long id = 1L;
        when(studentRepo.findById(id)).thenReturn(java.util.Optional.empty());
        // Act and Assert
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> studentService.getStudentById(id));
        assertEquals("Studente non presente all'interno del database!", ex.getMessage());
    }

    @Test
    void testGetPaginatedStudents() {
        Student student1 = new Student();
        Student student2 = new Student();
        Student student3 = new Student();
        List<Student> studentList = Arrays.asList(student1, student2, student3);

        int pageNumber = 0;
        int pageSize = 2;
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Student> mockPage = new PageImpl<>(studentList.subList(0, pageSize), pageable, studentList.size());

        when(studentRepo.findAll(any(Pageable.class))).thenReturn(mockPage);

        Page<Student> resultPage = studentService.getAllStudents(pageable);
        assertEquals(pageSize, resultPage.getContent().size(), "La dimensione della pagina dovrebbe essere " + pageSize);
        assertEquals(studentList.size(), resultPage.getTotalElements(), "Il totale degli elementi dovrebbe essere " + studentList.size());
        assertEquals((int) Math.ceil((double) studentList.size() / pageSize), resultPage.getTotalPages(), "Il totale delle pagine non corrisponde");
        assertEquals(pageNumber, resultPage.getNumber(), "Il numero di pagina non corrisponde");
        assertEquals(pageSize, resultPage.getSize(), "La dimensione della pagina non corrisponde");
        assertEquals(Arrays.asList(student1, student2), resultPage.getContent(), "Il contenuto della pagina non corrisponde all'atteso.");
        assertNotNull(resultPage, "La pagina non dovrebbe essere nulla.");
        assertTrue(resultPage.isFirst(), "Dovrebbe essere la prima pagina.");
        assertFalse(resultPage.isLast(), "Non dovrebbe essere l'ultima pagina.");

        verify(studentRepo, times(1)).findAll(pageable);
    }
    
    @Test
    public void testSaveStudent(){
        Student newStudent = new Student("Test", "test" , "4B", "alice@example.com");
        when(studentRepo.save(newStudent)).thenReturn(new Student(1L, "Test", "test" , "4B", "alice@example.com"));

        // Act
        studentService.saveStudent(newStudent);

        // Assert
        verify(studentRepo, times(1)).save(newStudent);
    }

    @Test
    public void testDeleteStudent() {
        // Arrange
        long studentId = 1L;
        when(studentRepo.existsById(studentId)).thenReturn(true);

        // Act
        studentService.deleteStudent(studentId);

        // Assert
        verify(studentRepo, times(1)).existsById(studentId);
        verify(studentRepo, times(1)).deleteById(studentId);
    }

    @Test
    public void testUpdateStudent() {
        // Arrange
        long studentId = 1L;
        Student updatedStudent = new Student(studentId, "Updated Name", "Updated Surname", "4b","updated.email@example.com");
        when(studentRepo.existsById(studentId)).thenReturn(true);
        when(studentRepo.save(updatedStudent)).thenReturn(updatedStudent);

        // Act
        Student result = studentService.updateStudent(updatedStudent);

        // Assert
        verify(studentRepo, times(1)).existsById(studentId);
        verify(studentRepo, times(1)).save(updatedStudent);

    }

}
