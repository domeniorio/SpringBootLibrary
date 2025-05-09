package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Libro;
import it.bitify.libreria.service.LibroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


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
    public Page<Libro> getAll(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size,
    @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.getAllLibri(pageable);
    }

    @DeleteMapping("/{id}")
    public void deleteLibro(@PathVariable Long id){
        service.deleteLibro(id);
    }

    @GetMapping("/findName")
    public Page<Libro> findByTitleContaining(@RequestParam String query,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size,
    @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.findByTitleContaining(query, pageable);
    }

    @GetMapping("/findYear")
    public Page<Libro> findByAnnoBetween(@RequestParam int start,
    @RequestParam(defaultValue = "2025") int end,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size,
    @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.findByAnnoBetween(start, end, pageable);
    }
}
