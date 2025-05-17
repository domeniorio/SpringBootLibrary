package it.bitify.libreria.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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


@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc; // Inietta MockMvc
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
    public void testGetStudentIdTypeMysmatch() throws Exception{
        String studentId = "test";

        ResultActions result = mockMvc.perform(get("/api/v1/student/{id}", studentId));
        result.andExpect(status().isBadRequest());
    }


    @Test
    public void testAddStudent() throws Exception{
        Student newStudent = new Student();
        newStudent.setName("Mario");
        newStudent.setSurname("Prappo");
        newStudent.setSchoolClass("4B");
        newStudent.setEmail("mario.prappo@example.com");

        mockMvc.perform(post("/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newStudent))) // Converti l'oggetto in JSON
                .andExpect(status().isOk());
    }

        @Test
    public void testAddStudentMissingParameter() throws Exception{
        Student newStudent = new Student();
        newStudent.setSurname("Prappo");
        newStudent.setSchoolClass("4B");
        newStudent.setEmail("mario.prappo@example.com");

        mockMvc.perform(post("/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newStudent))) // Converti l'oggetto in JSON
                .andExpect(status().isConflict());
    }

    @Test
    public void testAddStudentInvalid() throws Exception{
        Student newStudent = new Student();
        newStudent.setName("Mario");
        newStudent.setSurname("Prappo");
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
        newStudent.setName("Mario");
        newStudent.setSurname("Prappo");
        newStudent.setSchoolClass("4B");
        newStudent.setEmail("mario.prappo2@example.com");

        mockMvc.perform(put("/api/v1/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newStudent))) // Converti l'oggetto in JSON
                .andExpect(status().isOk());

    }

    @Test
    public void testPutStudentViolateConstraints() throws Exception{
        Student newStudent = new Student();
        newStudent.seId(2L);
        newStudent.setName("Mario");
        newStudent.setSurname("Prappo");
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
    public void testGetAllStudents_NoParams() throws Exception {
        // Act
        ResultActions result = mockMvc.perform(get("/api/v1/student"));
    
        // Assert
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}


