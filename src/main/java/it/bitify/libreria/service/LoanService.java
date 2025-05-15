package it.bitify.libreria.service;

import it.bitify.libreria.model.entity.Loan;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoanService {

    Loan getLoanById(Long id);

    void saveLoan(Loan Loan);

    void updateLoan(Loan Loan);

    void deleteLoan(Long id);

    Page<Loan> getAllLoans(Pageable pageable);

    Page<Loan> findByEndDateIsNull(Pageable pageable);

}
