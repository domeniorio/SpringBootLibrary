package it.bitify.libreria.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.bitify.libreria.model.dto.IdStudentBookDTO;
import it.bitify.libreria.model.entity.Student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;


@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetStudentById() throws Exception {
        // Arrange
        long studentId = 1;

        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/student/{id}", studentId));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(studentId))
                .andExpect(jsonPath("$.name").value("Luca"))
                .andExpect(jsonPath("$.surname").value("Rossi"))
                .andExpect(jsonPath("$.email").value("luca.rossi@example.com"))
                .andExpect(jsonPath("$.schoolClass").value("3A"));
    }

    @Test
    public void testGetNonExistingStudent() throws Exception{
        long studentId = 1000;

        ResultActions result = mockMvc.perform(get("/api/v1/student/{id}", studentId));
        result.andExpect(status().isNotFound());
    }

    @Test
    public void testGetStudentIdTypeMismatch() throws Exception{
        String studentId = "test";

        ResultActions result = mockMvc.perform(get("/api/v1/student/{id}", studentId));
        result.andExpect(status().isBadRequest());
    }


    @Test
    public void testAddStudent() throws Exception{
        Student newStudent = new Student();
        newStudent.setName("Test");
        newStudent.setSurname("Test");
        newStudent.setSchoolClass("4B");
        newStudent.setEmail("test.test@example.com");

        mockMvc.perform(post("/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newStudent))) // Converti l'oggetto in JSON
                .andExpect(status().isOk());
    }

        @Test
    public void testAddStudentMissingParameter() throws Exception{
        Student newStudent = new Student();
        newStudent.setSurname("Test");
        newStudent.setSchoolClass("4B");
        newStudent.setEmail("test.test@example.com");

        mockMvc.perform(post("/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newStudent))) // Converti l'oggetto in JSON
                .andExpect(status().isConflict());
    }

    @Test
    public void testAddStudentInvalid() throws Exception{
        Student newStudent = new Student();
        newStudent.setName("Test");
        newStudent.setSurname("Test");
        newStudent.setSchoolClass("4B");
        newStudent.setEmail("domenico.iorio@example.com");

        mockMvc.perform(post("/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newStudent))) // Converti l'oggetto in JSON
                .andExpect(status().isConflict());

    }

    @Test
    public void testPutStudent() throws Exception{
        Student newStudent = new Student();
        newStudent.seId(2L);
        newStudent.setName("Test");
        newStudent.setSurname("Test");
        newStudent.setSchoolClass("4B");
        newStudent.setEmail("test2.test@example.com");

        mockMvc.perform(put("/api/v1/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newStudent))) // Converti l'oggetto in JSON
                .andExpect(status().isOk());

    }

    @Test
    public void testPutStudentViolateConstraints() throws Exception{
        long studentId = 2;
        Student newStudent = new Student();
        newStudent.seId(studentId);
        newStudent.setName("Test");
        newStudent.setSurname("Test");
        newStudent.setSchoolClass("4B");
        newStudent.setEmail("domenico.iorio@example.com");

        mockMvc.perform(put("/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newStudent))) // Converti l'oggetto in JSON
                .andExpect(status().isConflict());

    }

    @Test
    public void testDeleteStudentWithLoan() throws Exception {
        // Arrange
        long studentId = 1;

        // Act
        ResultActions result = mockMvc.perform(delete("/api/v1/student/{id}", studentId));

        // Assert
        result.andExpect(status().isConflict());
    }

    @Test
    public void testDeleteNonExistingStudent() throws Exception {
        // Arrange
        long studentId = 100;

        // Act
        ResultActions result = mockMvc.perform(delete("/api/v1/student/{id}", studentId));

        // Assert
        result.andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteStudentWithoutLoan() throws Exception {
        // Arrange
        long studentId = 41;

        // Act
        ResultActions result = mockMvc.perform(delete("/api/v1/student/{id}", studentId));

        // Assert
        result.andExpect(status().isOk());
    }

    
    @Test
    public void testGetAllStudents_Pagination() throws Exception {
        // Arrange
        int page = 1;
        int size = 10;
        String sortBy = "id";
        boolean ascending = true;
    
        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/student")
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size))
                .param("sortBy", sortBy)
                .param("ascending", String.valueOf(ascending)));
    
        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    
    
    @Test
    public void testGetAllStudents_InvalidPage() throws Exception {
        // Arrange
        int page = -1;
        int size = 5;
        String sortBy = "id";
        boolean ascending = true;
    
        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/student")
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size))
                .param("sortBy", sortBy)
                .param("ascending", String.valueOf(ascending)));
    
        // Assert
        result.andExpect(status().isBadRequest());
    }
    
    
    @Test
    public void testGetAllStudentsWithoutLoan() throws Exception {
        // Arrange
        int page = 1;
        int size = 10;
        String sortBy = "id";
        boolean ascending = true;
    
        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/student/without-loan")
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size))
                .param("sortBy", sortBy)
                .param("ascending", String.valueOf(ascending)));
    
        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetAllStudentsWithoutLoan_invalidParameter() throws Exception {
        // Arrange
        int page = 1;
        int size = 10;
        String sortBy = "test";
        boolean ascending = true;
    
        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/student/without-loan")
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size))
                .param("sortBy", sortBy)
                .param("ascending", String.valueOf(ascending)));
    
        // Assert
        result.andExpect(status().isBadRequest());
    }

        @Test
    public void testGetAllStudentsByCardReleaseDate() throws Exception {
        // Arrange
        int page = 1;
        int size = 10;
        String sortBy = "id";
        boolean ascending = true;
        LocalDate start = LocalDate.of(2023, 1, 1);
        LocalDate end = LocalDate.of(2023, 12, 31); 
    
        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/student/by-card-release")
                .param("start", String.valueOf(start))
                .param("end", String.valueOf(end))
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size))
                .param("sortBy", sortBy)
                .param("ascending", String.valueOf(ascending)));
    
        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetAllStudentsByCardReleaseDate_NoParameters() throws Exception {
        // Arrange

        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/student/by-card-release"));
    
        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetAllStudentsByCardReleaseDate_InvalidParameters() throws Exception {
        // Arrange
        int page = 1;
        int size = 10;
        String sortBy = "id";
        boolean ascending = true;
        String start = "start";
    
        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/student/without-loan")
                .param("start", String.valueOf(start))
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size))
                .param("sortBy", sortBy)
                .param("ascending", String.valueOf(ascending)));
    
        // Assert
        result.andExpect(status().isOk());
    }

    @Test
    public void testGetAllStudentsByMinLoan() throws Exception {
        // Arrange
        int page = 1;
        int size = 10;
        String sortBy = "id";
        boolean ascending = true;
        int minLoan = 1;
        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/student/min-loan")
                .param("minLoan", String.valueOf(minLoan))
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size))
                .param("sortBy", sortBy)
                .param("ascending", String.valueOf(ascending)));
    
        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testGetAllStudentsByMinLoan_MissingParameters() throws Exception {
        // Arrange
        int page = 1;
        int size = 10;
        String sortBy = "id";
        boolean ascending = true;
        
    
        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/student/min-loan")
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size))
                .param("sortBy", sortBy)
                .param("ascending", String.valueOf(ascending)));
    
        // Assert
        result.andExpect(status().isBadRequest());
    }

    @Test
    public void testLoanBook() throws Exception{
        IdStudentBookDTO idStudentBookDTO = new IdStudentBookDTO();
        idStudentBookDTO.setIdStudent(1L);
        idStudentBookDTO.setIdBook(5L);

        mockMvc.perform(post("/api/v1/student/loan-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(idStudentBookDTO))) // Converti l'oggetto in JSON
                .andExpect(status().isOk());
    }

    @Test
    public void testLoanBook_Invalid() throws Exception{
        IdStudentBookDTO idStudentBookDTO = new IdStudentBookDTO();
        idStudentBookDTO.setIdStudent(2L);
        idStudentBookDTO.setIdBook(12L);

        mockMvc.perform(post("/api/v1/student/loan-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(idStudentBookDTO))) // Converti l'oggetto in JSON
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testReturnBook() throws Exception{
        IdStudentBookDTO idStudentBookDTO = new IdStudentBookDTO();
        idStudentBookDTO.setIdStudent(2L);
        idStudentBookDTO.setIdBook(12L);

        mockMvc.perform(post("/api/v1/student/return-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(idStudentBookDTO))) // Converti l'oggetto in JSON
                .andExpect(status().isOk());
    }

    @Test
    public void testReturnBook_Invalid() throws Exception{
        IdStudentBookDTO idStudentBookDTO = new IdStudentBookDTO();
        idStudentBookDTO.setIdStudent(1L);
        idStudentBookDTO.setIdBook(6L);

        mockMvc.perform(post("/api/v1/student/return-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(idStudentBookDTO))) // Converti l'oggetto in JSON
                .andExpect(status().isNotFound());
    }

    @Test
    public void testStats() throws Exception{

        Long idStudent = 1L;

        ResultActions result = mockMvc.perform(get("/api/v1/student/stats")
                .param("idStudent", String.valueOf(idStudent)));
                
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalLoans").value(4L))
                .andExpect(jsonPath("$.currentLoans").value(2L));
    }

    @Test
    public void testStats_Invalid() throws Exception{

        String idStudent = "test";

        ResultActions result = mockMvc.perform(get("/api/v1/student/stats")
                .param("idStudent", String.valueOf(idStudent)));
                
        result.andExpect(status().isBadRequest());
    }
}



