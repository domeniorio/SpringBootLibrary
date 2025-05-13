package it.bitify.libreria.service;

import it.bitify.libreria.model.entity.Book;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface BookService {

    Book findBookById(Long id);
    void saveBook(Book Book);
    void updateBook(Book Book);
    void deleteBook(Long id);
    Page<Book> findAllBooks(Pageable pageable);
    Page<Book> findByTitleContaining(String infix, Pageable pageable);
    Page<Book> findByPublishYearGreaterThan(Integer year, Pageable pageable);
    Page<Book> findByCategory_Name(String name, Pageable pageable);
    Page<Book> findBookWithoutLoan(Pageable pageable);
    Page<String> findBookLoanInternval(LocalDate start, Pageable pageable);
    Page<Book> findBooksWithLoanAboveAverage(Pageable pageable);


}
