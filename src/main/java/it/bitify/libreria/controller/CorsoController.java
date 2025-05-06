package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Corso;
import it.bitify.libreria.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/corso")
public class CorsoController {

    @Autowired
    CorsoService service;

    @GetMapping("/{id}")
    public Corso getCorsoById(@PathVariable int id){
        return service.getCorsoById(id);
    }

    @PostMapping("/aggiungi")
    public void aggiungiCorso(@RequestBody Corso corso){
        service.saveCorso(corso);
    }

    @PutMapping("/modifica/{id}")
    public void modificaCorso(@RequestBody Corso corso, @PathVariable int id){
        service.updateCorso(corso, id);
    }

    @GetMapping("/")
    public List<Corso> getAll(){
        return service.getAllCorsi();
    }

    @DeleteMapping("/{id}")
    public void deleteCorso(@PathVariable int id){
        service.deleteCorso(id);
    }
}
