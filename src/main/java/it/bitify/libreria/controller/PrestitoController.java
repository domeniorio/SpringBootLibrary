package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Libro;
import it.bitify.libreria.entity.Prestito;
import it.bitify.libreria.entity.Studente;
import it.bitify.libreria.service.LibroService;
import it.bitify.libreria.service.PrestitoService;
import it.bitify.libreria.service.StudenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/prestito")
public class PrestitoController {

    @Autowired
    PrestitoService service;

    @Autowired
    StudenteService studenteService;

    @Autowired
    LibroService libroService;


    @GetMapping("/{id}")
    public Prestito getPrestitoById(@PathVariable Long id){
        return service.getPrestitoById(id);
    }

    @PostMapping
    public void aggiungiPrestito(@RequestBody Prestito Prestito){
        service.savePrestito(Prestito);
    }

    @PutMapping
    public void modificaPrestito(@RequestBody Prestito Prestito) {
        service.updatePrestito(Prestito);
    }

    @GetMapping
    public Page<Prestito> getAll(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size,
    @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.getAllPrestiti(pageable);
    }

    @DeleteMapping("/{id}")
    public void deletePrestito(@PathVariable Long id){
        service.deletePrestito(id);
    }

    @GetMapping("/find")
    public Page<Prestito> getByStudenteOrLibro(@RequestParam(required = false) Long idStudente,
    @RequestParam(required = false) Long idLibro,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size,
    @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Studente studente = studenteService.getStudenteById(idStudente);
        Libro libro = libroService.getLibroById(idLibro);
        return service.findByStudenteOrLibro(studente,libro,pageable);
    }
}
