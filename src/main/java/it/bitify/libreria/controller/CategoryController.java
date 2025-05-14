package it.bitify.libreria.controller;

import it.bitify.libreria.model.dto.CategoriesBookCountDTO;
import it.bitify.libreria.model.entity.Category;
import it.bitify.libreria.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    
    @Autowired
    private CategoryService service;

    @GetMapping("/{id}")
    Category getCategoryById(@PathVariable Long id){
        return service.getCategoryById(id);
    }

    @PostMapping
    void addCategory(@RequestBody Category Category){
        service.saveCategory(Category);
    }

    @PutMapping
    void editCategory(@RequestBody Category category){
        service.updateCategory(category);
    }

    @DeleteMapping("/{id}")
    void removeCategory(@PathVariable Long id){
        service.deleteCategory(id);
    }

    @GetMapping
    Page<Category> getAllCategories(@RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @RequestParam(defaultValue = "id") String sortBy,
        @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.getAllCategorie(pageable);
    }

    @GetMapping("/book-count")
    Page<CategoriesBookCountDTO> getCategoriesWithBookCount(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return service.findCategoriesWithBookCount(pageable);

    }

    @GetMapping("/only-class")
    Page<Category> getCategoryOnlyClass(@RequestParam String targetClass,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.findCategoriesWithBooksLentOnlyByClass(targetClass,pageable);
    }



}
