package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Categoria;
import it.bitify.libreria.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaController {
    
    @Autowired
    private CategoriaService service;

    @GetMapping("/{id}")
    Categoria getCategoriaById(@PathVariable Long id){
        return service.getCategoriaById(id);
    }

    @PostMapping
    void aggiungiCategoria(@RequestBody Categoria Categoria){
        service.saveCategoria(Categoria);
    }

    @PutMapping
    void modificaCategoria(@RequestBody Categoria categoria){
        service.updateCategoria(categoria);
    }

    @DeleteMapping("/{id}")
    void rimuoviCategoria(@PathVariable Long id){
        service.deleteCategoria(id);
    }

    @GetMapping
    Page<Categoria> getAllCategorie(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size,
    @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.getAllCategorie(pageable);
    }
}
