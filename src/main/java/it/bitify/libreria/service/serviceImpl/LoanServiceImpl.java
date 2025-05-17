package it.bitify.libreria.service.serviceImpl;

import it.bitify.libreria.model.entity.Book;
import it.bitify.libreria.model.entity.Loan;
import it.bitify.libreria.exception.EntityNotFoundException;
import it.bitify.libreria.model.entity.Student;
import it.bitify.libreria.repository.LoanRepo;
import it.bitify.libreria.service.LoanService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepo repo;

    Logger logger = LogManager.getLogger(LoanServiceImpl.class);

    @Override
    public Loan getLoanById(Long id) {
        return repo.findById(id).orElseThrow(() -> {
            EntityNotFoundException ex = new EntityNotFoundException("Prestito non presente all'interno del database!");
            logger.error("Errore durante il recupero dell prestito con ID {}", id, ex);
            return ex;
        });
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "loans", key = " 'allLoans' "),
            @CacheEvict(value = "loans", key = " 'ongoing' ")
    })
    public void saveLoan(Loan Loan) {
        repo.save(Loan);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "loans", key = " 'allLoans' "),
            @CacheEvict(value = "loans", key = " 'ongoing' "),
            @CacheEvict(value = "stats", key = "#newLoan.getStudent().getId()")
    })
    public void updateLoan(Loan newLoan) {
        if (repo.existsById(newLoan.getLoanId())) {
            repo.save(newLoan);
        } else {
            EntityNotFoundException ex = new EntityNotFoundException("Prestito non presente all'interno del database!");
            logger.error("Errore durante il recupero dell prestito con ID {}", newLoan.getLoanId(), ex);
            throw ex;
        }

    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "loans", key = " 'allLoans' "),
            @CacheEvict(value = "loans", key = " 'ongoing' "),
    })
    public void deleteLoan(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        } else {
            EntityNotFoundException ex = new EntityNotFoundException("Prestito non presente all'interno del database!");
            logger.error("Errore durante il recupero dell prestito con ID {}", id, ex);
            throw ex;
        }
        Loan loanToDelete = getLoanById(id);
        evictStudentStatsCache(loanToDelete.getStudent().getId());
    }

    @CacheEvict(value = "stats", key = "#studentId")
    public void evictStudentStatsCache(Long studentId) {
        // Questo metodo non fa nulla, serve solo a triggerare l'eviction della cache.
        // Può essere chiamato da altri metodi quando si modifica qualcosa che impatta
        // gli stats dello studente.
    }

    @Override
    @Cacheable(value = "loans", key = " 'allLoans' ")
    public Page<Loan> getAllLoans(Pageable pageable) {
        logger.debug("lista di prestiti restituita");
        return repo.findAll(pageable);
    }

    @Override
    @Cacheable(value = "loans", key = " 'ongoing' ")
    public Page<Loan> findOngoingLoans(Pageable pageable) {
        return repo.findByEndDateIsNull(pageable);
    }

    @Override
    public boolean bookAlreadyLent(Book book) {
        return repo.findByBookAndEndDateIsNull(book).isPresent();
    }

    @Override
    public Optional<Loan> loanExists(Book book, Student student) {
        return repo.findByStudentAndBookAndEndDateIsNull(student, book);
    }

}
