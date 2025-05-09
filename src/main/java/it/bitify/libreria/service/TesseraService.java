package it.bitify.libreria.service;


import it.bitify.libreria.entity.Tessera;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TesseraService {

    Tessera getTesseraById(Long id);

    void saveTessera(Tessera tessera);

    void updateTessera(Tessera tessera);

    void deletetessera(Long id);

    Page<Tessera> getAllTessere(Pageable pageable);

}
