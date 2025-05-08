package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Corso;
import it.bitify.libreria.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/corso")
public class CorsoController {

    @Autowired
    CorsoService service;

    @GetMapping("/{id}")
    public Corso getCorsoById(@PathVariable Long id){
        return service.getCorsoById(id);
    }

    @PostMapping
    public void aggiungiCorso(@RequestBody Corso corso){
        service.saveCorso(corso);
    }

    @PutMapping
    public void modificaCorso(@RequestBody Corso corso){
        service.updateCorso(corso);
    }

    @GetMapping
    public List<Corso> getAll(){
        return service.getAllCorsi();
    }

    @DeleteMapping("/{id}")
    public void deleteCorso(@PathVariable Long id){
        service.deleteCorso(id);
    }
}
