package it.bitify.libreria.repository;

import it.bitify.libreria.entity.Book;
import it.bitify.libreria.entity.Loan;
import it.bitify.libreria.entity.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepo extends JpaRepository<Loan, Long> {
    Page<Loan> findByEndDateIsNull(Pageable pageable);
}
