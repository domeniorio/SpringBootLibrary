package it.bitify.libreria.service;

import it.bitify.libreria.entity.Corso;

import java.util.List;

public interface CorsoService {

    Corso getCorsoById(int id);

    void saveCorso(Corso corso);

    void updateCorso(Corso corso);

    void deleteCorso(int id);

    List<Corso> getAllCorsi();
}
