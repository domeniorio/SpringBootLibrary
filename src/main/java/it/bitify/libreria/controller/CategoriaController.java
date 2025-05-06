package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Categoria;
import it.bitify.libreria.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    
    @Autowired
    private CategoriaService service;

    @GetMapping("/{id}")
    Categoria getCategoriaById(@PathVariable int id){
        return service.getCategoriaById(id);
    }

    @PostMapping("/aggiungi")
    void aggiungiCategoria(@RequestBody Categoria Categoria){
        service.saveCategoria(Categoria);
    }

    @PutMapping("/modifica/{id}")
    void modificaCategoria(@RequestBody Categoria Categoria, @PathVariable int id){
        service.updateCategoria(Categoria,id);
    }

    @DeleteMapping("/{id}")
    void rimuoviCategoria(@PathVariable int id){
        service.deleteCategoria(id);
    }

    @GetMapping("/")
    List<Categoria> getAllCategorie(){
        return service.getAllCategorie();
    }
}
