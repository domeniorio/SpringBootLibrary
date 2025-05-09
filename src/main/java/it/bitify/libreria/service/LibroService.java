package it.bitify.libreria.service;

import it.bitify.libreria.entity.Libro;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LibroService {

    Libro getLibroById(Long id);

    void saveLibro(Libro libro);

    void updateLibro(Libro libro);

    void deleteLibro(Long id);

    Page<Libro> getAllLibri(Pageable pageable);

    Page<Libro> findByTitleContaining(String infix, Pageable pageable);
    Page<Libro> findByAnnoBetween(Integer startYear, Integer endYear, Pageable pageable);

}
