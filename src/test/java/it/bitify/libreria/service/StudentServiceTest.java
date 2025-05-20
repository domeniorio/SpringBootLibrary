package it.bitify.libreria.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import it.bitify.libreria.model.dto.CategoryOrderingDTO;
import it.bitify.libreria.model.dto.StudentStatsDTO;
import it.bitify.libreria.model.entity.Category;
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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
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
        assertEquals(student, result);
    }

    @Test
    public void testGetStudentById_NotFound() {
        // Arrange
        Long id = 1L;
        when(studentRepo.findById(id)).thenReturn(java.util.Optional.empty());
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

        verify(studentRepo, times(1)).save(newStudent);
    }

    @Test
    public void testDeleteStudent() {
        // Arrange
        long studentId = 1L;
        when(studentRepo.existsById(studentId)).thenReturn(true);

        // Act
        studentService.deleteStudent(studentId);

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

        verify(studentRepo, times(1)).existsById(studentId);
        verify(studentRepo, times(1)).save(updatedStudent);

    }

    @Test
    public void testStudentStats() {

        Student student = new Student(1L, "Domenico", "Iorio", "5c", "domenico.iorio@example.com");
        StudentStatsDTO studentStats = new StudentStatsDTO();

        studentStats.setTotalLoans(7L);
        studentStats.setCurrentLoans(5L);
        studentStats.setLastLoanDate(LocalDate.of(2023, 1, 15));
        Category cateogoryFantasy = new Category(1L, "Fantasy");
        Category cateogorySaggistica = new Category(2L, "Saggistica");
        Category cateogoryFantascienza = new Category(3L, "Fantascienza");


        CategoryOrderingDTO catOrder1 = new CategoryOrderingDTO(cateogoryFantasy, 5L, LocalDate.of(2023, 5, 10)); // Top by book count
        CategoryOrderingDTO catOrder2 = new CategoryOrderingDTO(cateogorySaggistica, 3L, LocalDate.of(2023, 6, 1));
        CategoryOrderingDTO catOrder3 = new CategoryOrderingDTO(cateogoryFantascienza, 7L, LocalDate.of(2023, 4, 20)); // This should be picked


        when(studentRepo.existsById(student.getId())).thenReturn(true);
        when(studentRepo.findLoanStats(student.getId())).thenReturn(studentStats);
        when(studentRepo.findTopCategoryByStudentId(student.getId())).thenReturn(Arrays.asList(catOrder1, catOrder2, catOrder3));

        // Act
        StudentStatsDTO result = studentService.studentStats(student.getId());

        assertNotNull(result);
        assertNotNull(result.getFavouriteCategory());
        assertEquals(cateogoryFantascienza.getName(), result.getFavouriteCategory().getName());

    }


    @Test
    void testStudentStats_StudentNotFound() {
        when(studentRepo.existsById(100L)).thenReturn(false);

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            studentService.studentStats(100L);
        });

        assertEquals("Studente non presente all'interno del database!", thrown.getMessage());
        verify(studentRepo, never()).findLoanStats(anyLong());
        verify(studentRepo, never()).findTopCategoryByStudentId(anyLong());
    }

    @Test
    public void testStudentStats_NoCategory() {

        Student student = new Student(1L, "Domenico", "Iorio", "5c", "domenico.iorio@example.com");
        StudentStatsDTO studentStats = new StudentStatsDTO();

        when(studentRepo.existsById(student.getId())).thenReturn(true);
        when(studentRepo.findLoanStats(student.getId())).thenReturn(studentStats);
        when(studentRepo.findTopCategoryByStudentId(student.getId())).thenReturn(Collections.emptyList());

        // Act
        StudentStatsDTO result = studentService.studentStats(student.getId());

        assertNotNull(result);
        assertNull(result.getFavouriteCategory());
        verify(studentRepo, times(1)).existsById(student.getId());
        verify(studentRepo, times(1)).findLoanStats(student.getId());
        verify(studentRepo, times(1)).findTopCategoryByStudentId(student.getId());
    }

}
