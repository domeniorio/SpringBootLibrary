package it.bitify.libreria.service;


import it.bitify.libreria.entity.Tessera;

import java.util.List;

public interface TesseraService {

    Tessera getTesseraById(int id);

    void saveTessera(Tessera tessera);

    void updateTessera(Tessera tessera, int id);

    void deletetessera(int id);

    List<Tessera> getAllTessere();

}
