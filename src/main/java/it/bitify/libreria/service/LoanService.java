package it.bitify.libreria.service;

import it.bitify.libreria.model.entity.Book;
import it.bitify.libreria.model.entity.Category;
import it.bitify.libreria.model.entity.Loan;

import it.bitify.libreria.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface LoanService {

    Loan getLoanById(Long id);

    void saveLoan(Loan Loan);

    void updateLoan(Loan Loan);

    void deleteLoan(Long id);

    Page<Loan> getAllLoans(Pageable pageable);

    Page<Loan> findByEndDateIsNull(Pageable pageable);

    boolean bookAlreadyLent(Book book);

    Optional<Loan> loanExists(Book book, Student student);

}
