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
    public Categoria getCategoriaById(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
    }

    @Override
    public void saveCategoria(Categoria categoria) {
        repo.save(categoria);
    }

    @Override
    public void updateCategoria(Categoria nuovaCategoria) {
        if(repo.existsById(nuovaCategoria.getCodiceCategoria())){
            repo.save(nuovaCategoria);
        }
        else new EntityNotFoundException("Valore non presente all'interno del database!");

    }

    @Override
    public void deleteCategoria(Long id) {
        if(repo.existsById(id))  repo.deleteById(id);
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public List<Categoria> getAllCategorie() {
        return repo.findAll();
    }
}
