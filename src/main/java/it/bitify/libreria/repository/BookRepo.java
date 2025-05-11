package it.bitify.libreria.repository;

import it.bitify.libreria.entity.Book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {
    Page<Book> findByTitleContaining(String infix, Pageable pageable);
    Page<Book> findByYearBetween(Integer startYear, Integer endYear, Pageable pageable);
}
