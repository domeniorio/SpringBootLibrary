package it.bitify.libreria.service.serviceImpl;

import it.bitify.libreria.model.dto.CategoriesBookCountDTO;
import it.bitify.libreria.model.entity.Category;
import it.bitify.libreria.exception.EntityNotFoundException;
import it.bitify.libreria.repository.CategoryRepo;
import it.bitify.libreria.service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo repo;

    Logger logger = LogManager.getLogger(CategoryServiceImpl.class);

    @Override
    public Category getCategoryById(Long id) {
        Category category = repo.findById(id).orElseThrow(() -> {
            EntityNotFoundException ex = new EntityNotFoundException("Studente non presente all'interno del database!");
            logger.error("Errore durante il recupero dello studente con ID {}", id, ex);
            return ex;
        });
        logger.debug("Studente trovato con ID: {}", id);
        return category;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "categories", key = "'categoriesBookCount'"),
            @CacheEvict(value="categories", key=" 'allCategories' ")
    })
    public void saveCategory(Category category) {
        logger.debug("Categoria salvata con ID: {}", category.getIdCategory());
        repo.save(category);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value="books", key=" 'allBooks' "),
            @CacheEvict(value="categories", key=" 'allCategories' "),
            @CacheEvict(value = "categories", key = "'categoriesBookCount'")
    })
    public Category updateCategory(Category newCategory) {
        if(repo.existsById(newCategory.getIdCategory())) {
            logger.debug("Categoria modificata con ID: {}", newCategory.getIdCategory());
            repo.save(newCategory);
            return newCategory;
        }
        else{
            EntityNotFoundException ex = new EntityNotFoundException("Valore non presente all'interno del database!");
            logger.error("Errore durante il recupero della categoria con ID {}", newCategory.getIdCategory(), ex);
            throw ex;
        }
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value="books", key=" 'allBooks' "),
            @CacheEvict(value="categories", key=" 'allCategories' "),
            @CacheEvict(value = "categories", key = "'categoriesBookCount'")
    })
    public void deleteCategory(Long id) {
        if (repo.existsById(id)) {
            logger.debug("Categoria eliminata con ID: {}", id);
            repo.deleteById(id);
        } else {
            EntityNotFoundException ex = new EntityNotFoundException("Valore non presente all'interno del database!");
            logger.error("Errore durante il recupero della categoria con ID {}", id, ex);
            throw ex;
        }
    }

    @Override
    @Cacheable(value = "categories", key = "'allCategories'")
    public Page<Category> getAllCategories(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    @Cacheable(value = "categories", key = "'categoriesBookCount'")
    public Page<CategoriesBookCountDTO> findCategoriesWithBookCount(Pageable pageable) {
        return repo.findCategoriesWithBookCount(pageable);
    }

    @Override
    public Page<Category> findCategoriesWithBooksLentOnlyByClass(String targetClass, Pageable pageable) {
        return repo.findCategoriesWithBooksLentOnlyByClass(targetClass,pageable);
    }
}
