package it.bitify.libreria.controller;

import it.bitify.libreria.model.entity.Loan;
import it.bitify.libreria.service.LoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/loan")
public class LoanController {

    @Autowired
    LoanService service;

    @GetMapping("/{id}")
    public Loan getLoanById(@PathVariable Long id){
        return service.getLoanById(id);
    }

    @PostMapping
    public void addLoan(@RequestBody Loan Loan){
        service.saveLoan(Loan);
    }

    @PutMapping
    public void editLoan(@RequestBody Loan Loan) {
        service.updateLoan(Loan);
    }

    @GetMapping
    public Page<Loan> getAllLoans(@RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.getAllPrestiti(pageable);
    }

    @DeleteMapping("/{id}")
    public void deleteLoan(@PathVariable Long id){
        service.deleteLoan(id);
    }


    @GetMapping("/ongoing")
    public Page<Loan> getByEndDateIsNull(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.findByEndDateIsNull(pageable);
    }



}
