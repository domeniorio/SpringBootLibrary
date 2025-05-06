package it.bitify.libreria.service;

import it.bitify.libreria.entity.Studente;

import java.util.List;

public interface StudenteService {

    Studente getStudenteById(int id);

    void saveStudente(Studente studente);

    void updateStudente(Studente studente, int id);

    void deleteStudente(int id);

    List<Studente> getAllStudenti();
}
