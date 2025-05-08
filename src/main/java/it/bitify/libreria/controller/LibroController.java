package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Libro;
import it.bitify.libreria.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/libro")
public class LibroController {

    @Autowired
    LibroService service;

    @GetMapping("/{id}")
    public Libro getLibroById(@PathVariable Long id){
        return service.getLibroById(id);
    }

    @PostMapping
    public void aggiungiLibro(@RequestBody Libro Libro){
        service.saveLibro(Libro);
    }

    @PutMapping
    public void modificaLibro(@RequestBody Libro Libro){
        service.updateLibro(Libro);
    }

    @GetMapping
    public List<Libro> getAll(){
        return service.getAllLibri();
    }

    @DeleteMapping("/{id}")
    public void deleteLibro(@PathVariable Long id){
        service.deleteLibro(id);
    }
}
