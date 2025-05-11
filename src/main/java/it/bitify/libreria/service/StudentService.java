package it.bitify.libreria.service;

import it.bitify.libreria.entity.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {

    Student getStudentById(Long id);

    void saveStudent(Student Student);

    void updateStudent(Student Student);

    void deleteStudent(Long id);

    Page<Student> getAllStudents(Pageable pageable);

    Page<Student> getStudentBynameAndsurname(String name, String surname, Pageable pageable);
}
