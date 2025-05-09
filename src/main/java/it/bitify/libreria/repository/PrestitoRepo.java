package it.bitify.libreria.repository;

import it.bitify.libreria.entity.Libro;
import it.bitify.libreria.entity.Prestito;
import it.bitify.libreria.entity.Studente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestitoRepo extends JpaRepository<Prestito, Long> {
    Page<Prestito> findByStudenteOrLibro(Studente studente, Libro libro, Pageable pageable);
}
