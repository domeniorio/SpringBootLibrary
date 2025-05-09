package it.bitify.libreria.service.serviceImpl;

import it.bitify.libreria.entity.Corso;
import it.bitify.libreria.exception.EntityNotFoundException;
import it.bitify.libreria.repository.CorsoRepo;
import it.bitify.libreria.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CorsoServiceImpl implements CorsoService {

    @Autowired
    private CorsoRepo repo;

    @Override
    public Corso getCorsoById(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
    }

    @Override
    public void saveCorso(Corso Corso) {
        repo.save(Corso);
    }

    @Override
    public void updateCorso(Corso nuovoCorso) {
        if(repo.existsById(nuovoCorso.getCodiceCorso())){
            repo.save(nuovoCorso);
        }
        else new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public void deleteCorso(Long id) {
        if(repo.existsById(id))  repo.deleteById(id);
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public Page<Corso> getAllCorsi(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
