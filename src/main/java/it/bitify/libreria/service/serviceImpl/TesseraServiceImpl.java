package it.bitify.libreria.service.serviceImpl;

import it.bitify.libreria.entity.Tessera;
import it.bitify.libreria.exception.EntityNotFoundException;
import it.bitify.libreria.repository.TesseraRepo;
import it.bitify.libreria.service.TesseraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class TesseraServiceImpl implements TesseraService {

    @Autowired
    private TesseraRepo repo;

    @Override
    public Tessera getTesseraById(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
    }

    @Override
    public void saveTessera(Tessera tessera) {
        repo.save(tessera);
    }

    @Override
    public void updateTessera(Tessera nuovaTessera) {
        if (repo.existsById(nuovaTessera.getCodiceTessera())){
            repo.save(nuovaTessera);
        }
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public void deletetessera(Long id) {
        if(repo.existsById(id)){
            repo.deleteById(id);
        }
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public Page<Tessera> getAllTessere(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
