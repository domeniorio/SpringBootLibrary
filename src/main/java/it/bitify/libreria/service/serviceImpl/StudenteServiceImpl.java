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
    public Studente getStudenteById(int id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
    }

    @Override
    public void saveStudente(Studente studente) {
        repo.save(studente);
    }

    @Override
    public void updateStudente(Studente nuovoStudente, int id) {
        Studente vecchioStudente = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
        if(nuovoStudente.getNome() != null) vecchioStudente.setNome(nuovoStudente.getNome());
        if(nuovoStudente.getCognome() != null) vecchioStudente.setCognome(nuovoStudente.getCognome());
        if(nuovoStudente.getClasse() != null) vecchioStudente.setClasse(nuovoStudente.getClasse());
        if(nuovoStudente.getEmail() != null) vecchioStudente.setEmail(nuovoStudente.getEmail());
        repo.save(vecchioStudente);
    }

    @Override
    public void deleteStudente(int id) {
        if(repo.existsById(id))  repo.deleteById(id);
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public List<Studente> getAllStudenti() {
        return repo.findAll();
    }
}
