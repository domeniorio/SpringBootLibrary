package it.bitify.libreria.service;


import it.bitify.libreria.entity.Tessera;

import java.util.List;

public interface TesseraService {

    Tessera getTesseraById(Long id);

    void saveTessera(Tessera tessera);

    void updateTessera(Tessera tessera);

    void deletetessera(Long id);

    List<Tessera> getAllTessere();

}
