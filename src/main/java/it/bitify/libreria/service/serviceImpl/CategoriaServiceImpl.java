package it.bitify.libreria.service.serviceImpl;

import it.bitify.libreria.entity.Categoria;
import it.bitify.libreria.exception.EntityNotFoundException;
import it.bitify.libreria.repository.CategoriaRepo;
import it.bitify.libreria.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepo repo;

    @Override
    public Categoria getCategoriaById(int id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
    }

    @Override
    public void saveCategoria(Categoria Categoria) {
        repo.save(Categoria);
    }

    @Override
    public void updateCategoria(Categoria nuovaCategoria) {
        Categoria vecchiaCategoria = repo.findById(nuovaCategoria.getCodiceCategoria()).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
        repo.save(nuovaCategoria);
    }

    @Override
    public void deleteCategoria(int id) {
        if(repo.existsById(id))  repo.deleteById(id);
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public List<Categoria> getAllCategorie() {
        return repo.findAll();
    }
}
