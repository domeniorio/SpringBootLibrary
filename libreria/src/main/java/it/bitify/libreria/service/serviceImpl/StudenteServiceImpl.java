package it.bitify.libreria.service.serviceImpl;

import it.bitify.libreria.entity.Studente;
import it.bitify.libreria.exception.EntityNotFoundException;
import it.bitify.libreria.repository.StudenteRepo;
import it.bitify.libreria.service.StudenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudenteServiceImpl implements StudenteService {

    @Autowired
    private StudenteRepo repo;

    @Override
    public Studente getStudenteById(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
    }

    @Override
    public void saveStudente(Studente studente) {
        repo.save(studente);
    }

    @Override
    public void updateStudente(Studente nuovoStudente) {
        if(repo.existsById(nuovoStudente.getCodiceStudente())) {
            repo.save(nuovoStudente);
        }
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public void deleteStudente(Long id) {
        if(repo.existsById(id)) {
            repo.deleteById(id);
        }
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public List<Studente> getAllStudenti() {
        return repo.findAll();
    }
}
