package it.bitify.libreria.repository;

import it.bitify.libreria.entity.Libro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepo extends JpaRepository<Libro, Long> {

    Page<Libro> findByTitoloContaining(String infix, Pageable pageable);
    Page<Libro> findByAnnoBetween(Integer startYear, Integer endYear, Pageable pageable);
}
