package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Categoria;
import it.bitify.libreria.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    List<Categoria> getAllCategorie(){
        return service.getAllCategorie();
    }
}
