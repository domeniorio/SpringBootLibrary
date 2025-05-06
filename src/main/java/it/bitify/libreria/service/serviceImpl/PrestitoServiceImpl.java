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
    public Prestito getPrestitoById(int id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
    }

    @Override
    public void savePrestito(Prestito Prestito) {
        repo.save(Prestito);
    }

    @Override
    public void updatePrestito(Prestito nuovoPrestito, int id) {
        Prestito vecchioPrestito = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
        if(nuovoPrestito.getDataInizio() != null) vecchioPrestito.setDataInizio(nuovoPrestito.getDataInizio());
        if(nuovoPrestito.getDataFine() != null) vecchioPrestito.setDataFine(nuovoPrestito.getDataFine());
        if(nuovoPrestito.getStudente() != null) vecchioPrestito.setStudente(nuovoPrestito.getStudente());
        if(nuovoPrestito.getLibro() != null) vecchioPrestito.setLibro(nuovoPrestito.getLibro());
        repo.save(vecchioPrestito);
    }

    @Override
    public void deletePrestito(int id) {
        if(repo.existsById(id))  repo.deleteById(id);
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public List<Prestito> getAllPrestiti() {
        return repo.findAll();
    }
}
