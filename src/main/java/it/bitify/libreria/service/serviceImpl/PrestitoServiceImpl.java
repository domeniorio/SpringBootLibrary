package it.bitify.libreria.service.serviceImpl;

import it.bitify.libreria.entity.Prestito;
import it.bitify.libreria.exception.EntityNotFoundException;
import it.bitify.libreria.repository.PrestitoRepo;
import it.bitify.libreria.service.PrestitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestitoServiceImpl implements PrestitoService {

    @Autowired
    private PrestitoRepo repo;

    @Override
    public Prestito getPrestitoById(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
    }

    @Override
    public void savePrestito(Prestito Prestito) {
        repo.save(Prestito);
    }

    @Override
    public void updatePrestito(Prestito nuovoPrestito) {
        if(repo.existsById(nuovoPrestito.getCodicePrestito())){
            repo.save(nuovoPrestito);
        }
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");

    }

    @Override
    public void deletePrestito(Long id) {
        if(repo.existsById(id))  repo.deleteById(id);
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public List<Prestito> getAllPrestiti() {
        return repo.findAll();
    }
}
