package it.bitify.libreria.service;

import it.bitify.libreria.model.dto.NameSurnameLoansDTO;
import it.bitify.libreria.model.dto.StudentStatsDTO;
import it.bitify.libreria.model.entity.Book;
import it.bitify.libreria.model.entity.Loan;
import it.bitify.libreria.model.entity.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface StudentService {

    Student getStudentById(Long id);

    void saveStudent(Student Student);

    Student updateStudent(Student Student);

    void deleteStudent(Long id);

    Page<Student> getAllStudents(Pageable pageable);

    Page<Student> findBySchoolClass(String schoolClass, Pageable pageable);

    Page<Student> findByCard_ReleaseDateBetween(LocalDate start, LocalDate end, Pageable pageable);

    Page<Student> findStudentWithoutLoan(Pageable pageable);

    Page<NameSurnameLoansDTO> findStudentMoreThanLoans(int minLoan, Pageable pageable);

    Page<Student> findStudentLoanCategory(String categoryName, Pageable pageable);

    Page<Student> findStudentsOrderedByLoanCount(Pageable pageable);

    Loan loanBook(Long idStudent, Long idBook);

    Loan returnBook(Long idStudent, Long idBook);

    StudentStatsDTO studentStats(Long idStudent);

    Page<Book> suggestions(Long idStudent, Pageable pageable);
}
