package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Libro;
import it.bitify.libreria.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    LibroService service;

    @GetMapping("/{id}")
    public Libro getLibroById(@PathVariable int id){
        return service.getLibroById(id);
    }

    @PostMapping("/aggiungi")
    public void aggiungiLibro(@RequestBody Libro Libro){
        service.saveLibro(Libro);
    }

    @PutMapping("/modifica/{id}")
    public void modificaLibro(@RequestBody Libro Libro, @PathVariable int id){
        service.updateLibro(Libro, id);
    }

    @GetMapping("/")
    public List<Libro> getAll(){
        return service.getAllLibri();
    }

    @DeleteMapping("/{id}")
    public void deleteLibro(@PathVariable int id){
        service.deleteLibro(id);
    }
}
