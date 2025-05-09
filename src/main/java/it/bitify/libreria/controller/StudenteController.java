package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Studente;
import it.bitify.libreria.service.StudenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/studente")
public class StudenteController {

    @Autowired
    StudenteService service;

    @GetMapping("/{id}")
    public Studente getStudenteById(@PathVariable Long id){
        return service.getStudenteById(id);
    }

    @PostMapping
    public void aggiungiStudente(@RequestBody Studente studente){
        service.saveStudente(studente);
    }

    @PutMapping
    public void modificaStudente(@RequestBody Studente studente){
        service.updateStudente(studente);
    }

    @GetMapping
    public Page<Studente> getAll(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size,
    @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.getAllStudenti(pageable);
    }

    @DeleteMapping("/{id}")
    public void deleteStudente(@PathVariable Long id){
        service.deleteStudente(id);
    }

    @GetMapping("/find")
    public Page<Studente> getByNomeAndCognome(@RequestParam String nome,
    @RequestParam String cognome,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size,
    @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.getStudenteByNomeAndCognome(nome,cognome,pageable);
    }

}