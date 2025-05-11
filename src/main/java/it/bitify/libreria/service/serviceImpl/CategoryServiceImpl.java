package it.bitify.libreria.service.serviceImpl;

import it.bitify.libreria.entity.Category;
import it.bitify.libreria.exception.EntityNotFoundException;
import it.bitify.libreria.repository.CategoryRepo;
import it.bitify.libreria.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo repo;

    @Override
    public Category getCategoryById(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
    }

    @Override
    public void saveCategory(Category category) {
        repo.save(category);
    }

    @Override
    public void updateCategory(Category nuovaCategory) {
        if(repo.existsById(nuovaCategory.getIdCategory())){
            repo.save(nuovaCategory);
        }
        else new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public void deleteCategory(Long id) {
        if(repo.existsById(id))  repo.deleteById(id);
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public Page<Category> getAllCategorie(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
