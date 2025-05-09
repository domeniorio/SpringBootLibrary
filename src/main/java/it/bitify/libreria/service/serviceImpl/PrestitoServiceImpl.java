package it.bitify.libreria.service.serviceImpl;

import it.bitify.libreria.entity.Libro;
import it.bitify.libreria.entity.Prestito;
import it.bitify.libreria.entity.Studente;
import it.bitify.libreria.exception.EntityNotFoundException;
import it.bitify.libreria.repository.PrestitoRepo;
import it.bitify.libreria.service.PrestitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


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
    public Page<Prestito> getAllPrestiti(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Page<Prestito> findByStudenteOrLibro(Studente studente, Libro libro, Pageable pageable) {
        return repo.findByStudenteOrLibro(studente, libro, pageable);
    }
}
