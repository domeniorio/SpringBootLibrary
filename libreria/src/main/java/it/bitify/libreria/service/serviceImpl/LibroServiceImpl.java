package it.bitify.libreria.service.serviceImpl;

import it.bitify.libreria.entity.Libro;
import it.bitify.libreria.exception.EntityNotFoundException;
import it.bitify.libreria.repository.LibroRepo;
import it.bitify.libreria.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepo repo;

    @Override
    public Libro getLibroById(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
    }

    @Override
    public void saveLibro(Libro libro) {
        repo.save(libro);
    }

    @Override
    public void updateLibro(Libro nuovoLibro) {
        if(repo.existsById(nuovoLibro.getCodiceLibro())){
            repo.save(nuovoLibro);
        }
        else throw  new EntityNotFoundException("Valore non presente all'interno del database!");

    }

    @Override
    public void deleteLibro(Long id) {
        if(repo.existsById(id)) {
            repo.deleteById(id);
        }
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public List<Libro> getAllLibri() {
        return repo.findAll();
    }
}
