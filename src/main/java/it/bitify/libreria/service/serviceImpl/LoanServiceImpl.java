package it.bitify.libreria.service.serviceImpl;

import it.bitify.libreria.entity.Loan;
import it.bitify.libreria.exception.EntityNotFoundException;
import it.bitify.libreria.repository.LoanRepo;
import it.bitify.libreria.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepo repo;

    @Override
    public Loan getLoanById(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
    }

    @Override
    public void saveLoan(Loan Loan) {
        repo.save(Loan);
    }

    @Override
    public void updateLoan(Loan newLoan) {
        if(repo.existsById(newLoan.getLoanId())){
            repo.save(newLoan);
        }
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");

    }

    @Override
    public void deleteLoan(Long id) {
        if(repo.existsById(id))  repo.deleteById(id);
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public Page<Loan> getAllPrestiti(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Page<Loan> findByEndDateIsNull(Pageable pageable) {
        return repo.findByEndDateIsNull(pageable);
    }


}
