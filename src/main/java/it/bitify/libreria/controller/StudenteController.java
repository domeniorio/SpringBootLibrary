package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Studente;
import it.bitify.libreria.service.StudenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studente")
public class StudenteController {

    @Autowired
    StudenteService service;

    @GetMapping("/{id}")
    public Studente getStudenteById(@PathVariable int id){
        return service.getStudenteById(id);
    }

    @PostMapping("/aggiungi")
    public void aggiungiStudente(@RequestBody Studente studente){
        service.saveStudente(studente);
    }

    @PutMapping("/modifica/{id}")
    public void modificaStudente(@RequestBody Studente studente, @PathVariable int id){
        service.updateStudente(studente, id);
    }

    @GetMapping("/")
    public List<Studente> getAll(){
        return service.getAllStudenti();
    }

    @DeleteMapping("/{id}")
    public void deleteStudente(@PathVariable int id){
        service.deleteStudente(id);
    }
}
