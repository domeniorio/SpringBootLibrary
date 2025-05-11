package it.bitify.libreria.service;

import it.bitify.libreria.entity.Book;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    Book getBookById(Long id);
    void saveBook(Book Book);
    void updateBook(Book Book);
    void deleteBook(Long id);
    Page<Book> getAllBooks(Pageable pageable);
    Page<Book> findByTitleContaining(String infix, Pageable pageable);
    Page<Book> findByPublishYearGreaterThan(Integer year, Pageable pageable);
    Page<Book> findByCategory_Name(String name, Pageable pageable);
}
