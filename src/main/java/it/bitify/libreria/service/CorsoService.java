package it.bitify.libreria.service;

import it.bitify.libreria.entity.Corso;

import java.util.List;

public interface CorsoService {

    Corso getCorsoById(int id);

    void saveCorso(Corso Corse);

    void updateCorso(Corso Corse, int id);

    void deleteCorso(int id);

    List<Corso> getAllCorsi();
}
