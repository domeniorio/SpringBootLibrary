package it.bitify.libreria.service;

import it.bitify.libreria.entity.Prestito;

import java.util.List;

public interface PrestitoService {

    Prestito getPrestitoById(int id);

    void savePrestito(Prestito Prestito);

    void updatePrestito(Prestito Prestito, int id);

    void deletePrestito(int id);

    List<Prestito> getAllPrestiti();
}
