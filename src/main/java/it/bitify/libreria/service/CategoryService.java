package it.bitify.libreria.service;

import it.bitify.libreria.model.dto.CategoriesBookCountDTO;
import it.bitify.libreria.model.entity.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Category getCategoryById(Long id);

    void saveCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(Long id);

    Page<Category> getAllCategorie(Pageable pageable);

    Page<CategoriesBookCountDTO> findCategoriesWithBookCount(Pageable pageable);

    Page<Category> findCategoriesWithBooksLentOnlyByClass(String targetClass, Pageable pageable);

}
