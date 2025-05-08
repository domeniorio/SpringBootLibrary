package it.bitify.libreria.service;

import it.bitify.libreria.entity.Prestito;

import java.util.List;

public interface PrestitoService {

    Prestito getPrestitoById(Long id);

    void savePrestito(Prestito Prestito);

    void updatePrestito(Prestito Prestito);

    void deletePrestito(Long id);

    List<Prestito> getAllPrestiti();
}
