package it.bitify.libreria.service;

import it.bitify.libreria.entity.Studente;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudenteService {

    Studente getStudenteById(Long id);

    void saveStudente(Studente studente);

    void updateStudente(Studente studente);

    void deleteStudente(Long id);

    Page<Studente> getAllStudenti(Pageable pageable);
}
