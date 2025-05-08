package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Studente;
import it.bitify.libreria.service.StudenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public List<Studente> getAll(){
        return service.getAllStudenti();
    }

    @DeleteMapping("/{id}")
    public void deleteStudente(@PathVariable Long id){
        service.deleteStudente(id);
    }
}