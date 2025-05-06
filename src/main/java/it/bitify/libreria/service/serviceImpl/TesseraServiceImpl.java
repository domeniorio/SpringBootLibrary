package it.bitify.libreria.service.serviceImpl;

import it.bitify.libreria.entity.Tessera;
import it.bitify.libreria.exception.EntityNotFoundException;
import it.bitify.libreria.repository.TesseraRepo;
import it.bitify.libreria.service.TesseraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TesseraServiceImpl implements TesseraService {

    @Autowired
    private TesseraRepo repo;

    @Override
    public Tessera getTesseraById(int id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
    }

    @Override
    public void saveTessera(Tessera tessera) {
        repo.save(tessera);
    }

    @Override
    public void updateTessera(Tessera nuovaTessera, int id) {
        Tessera vecchiaTessera = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
        if(nuovaTessera.getDataRilascio() != null) vecchiaTessera.setDataRilascio(nuovaTessera.getDataRilascio());
        repo.save(vecchiaTessera);
    }

    @Override
    public void deletetessera(int id) {
        if(repo.existsById(id))  repo.deleteById(id);
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public List<Tessera> getAllTessere() {
        return repo.findAll();
    }
}
