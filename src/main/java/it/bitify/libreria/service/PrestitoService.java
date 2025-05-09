package it.bitify.libreria.service;

import it.bitify.libreria.entity.Libro;
import it.bitify.libreria.entity.Prestito;
import it.bitify.libreria.entity.Studente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PrestitoService {

    Prestito getPrestitoById(Long id);

    void savePrestito(Prestito Prestito);

    void updatePrestito(Prestito Prestito);

    void deletePrestito(Long id);

    Page<Prestito> getAllPrestiti(Pageable pageable);

    Page<Prestito> findByStudenteOrLibro(Studente studente, Libro libro, Pageable pageable);
}
