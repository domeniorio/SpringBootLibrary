package it.bitify.libreria.service;

import it.bitify.libreria.entity.Corso;

import java.util.List;

public interface CorsoService {

    Corso getCorsoById(Long id);

    void saveCorso(Corso corso);

    void updateCorso(Corso corso);

    void deleteCorso(Long id);

    List<Corso> getAllCorsi();
}
