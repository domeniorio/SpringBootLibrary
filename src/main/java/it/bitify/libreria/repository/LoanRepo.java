package it.bitify.libreria.repository;

import it.bitify.libreria.model.entity.Book;
import it.bitify.libreria.model.entity.Loan;
import it.bitify.libreria.model.entity.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepo extends JpaRepository<Loan, Long> {
    Page<Loan> findByEndDateIsNull(Pageable pageable);
    Optional<Loan> findByBookAndEndDateIsNull(Book book);
    Optional<Loan> findByStudentAndBookAndEndDateIsNull(Student studen, Book book);

}
