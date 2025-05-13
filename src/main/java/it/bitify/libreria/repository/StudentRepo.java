package it.bitify.libreria.repository;

import it.bitify.libreria.model.dto.NameSurnameLoansDTO;
import it.bitify.libreria.model.entity.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface StudentRepo extends JpaRepository<Student, Long> {
    Page<Student> findByNameAndSurname(String name, String surname, Pageable pageable);
    Page<Student> findBySchoolClass(String schoolClass, Pageable pageable);
    Page<Student> findByCard_ReleaseDateBetween(LocalDate start, LocalDate end, Pageable pageable);

    @Query("SELECT s FROM Student s LEFT JOIN s.loans l WHERE l.id IS NULL")
    Page<Student> findStudentWithoutLoan(Pageable pageable);

    @Query("SELECT NEW it.bitify.libreria.model.dto.NameSurnameLoansDTO(s.name, s.surname, COUNT(l.id)) FROM Student s LEFT JOIN s.loans l GROUP BY s.id HAVING COUNT(l.id)>=:minLoan")
    Page<NameSurnameLoansDTO> findStudentMoreThanLoans(@Param("minLoan")int minLoan, Pageable pageable);

    @Query("SELECT s FROM Student s JOIN s.loans l JOIN l.book b JOIN b.category c WHERE c.name = :categoryName ")
    Page<Student> findStudentLoanCategory(@Param("categoryName") String cName, Pageable pageable);

    @Query("SELECT s FROM Student s JOIN s.loans l GROUP BY s.id ORDER BY COUNT(l.id) DESC")
    Page<Student> findStudentsOrderedByLoanCount(Pageable pageable);
}
