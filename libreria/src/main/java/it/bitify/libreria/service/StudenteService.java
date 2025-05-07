package it.bitify.libreria.service;

import it.bitify.libreria.entity.Studente;

import java.util.List;

public interface StudenteService {

    Studente getStudenteById(Long id);

    void saveStudente(Studente studente);

    void updateStudente(Studente studente);

    void deleteStudente(Long id);

    List<Studente> getAllStudenti();
}
