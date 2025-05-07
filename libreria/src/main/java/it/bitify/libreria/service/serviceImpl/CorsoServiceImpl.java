package it.bitify.libreria.service.serviceImpl;

import it.bitify.libreria.entity.Corso;
import it.bitify.libreria.exception.EntityNotFoundException;
import it.bitify.libreria.repository.CorsoRepo;
import it.bitify.libreria.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorsoServiceImpl implements CorsoService {

    @Autowired
    private CorsoRepo repo;

    @Override
    public Corso getCorsoById(int id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
    }

    @Override
    public void saveCorso(Corso Corso) {
        repo.save(Corso);
    }

    @Override
    public void updateCorso(Corso nuovoCorso) {
        Corso vecchioCorso = repo.findById(nuovoCorso.getCodiceCorso()).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
        repo.save(nuovoCorso);
    }

    @Override
    public void deleteCorso(int id) {
        if(repo.existsById(id))  repo.deleteById(id);
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public List<Corso> getAllCorsi() {
        return repo.findAll();
    }
}
