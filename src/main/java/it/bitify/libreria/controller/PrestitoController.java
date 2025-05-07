package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Prestito;
import it.bitify.libreria.service.PrestitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prestito")
public class PrestitoController {

    @Autowired
    PrestitoService service;

    @GetMapping("/{id}")
    public Prestito getPrestitoById(@PathVariable int id){
        return service.getPrestitoById(id);
    }

    @PostMapping("/aggiungi")
    public void aggiungiPrestito(@RequestBody Prestito Prestito){
        service.savePrestito(Prestito);
    }

    @PutMapping("/modifica/{id}")
    public void modificaPrestito(@RequestBody Prestito Prestito, @PathVariable int id) { service.updatePrestito(Prestito, id); }

    @GetMapping("/")
    public List<Prestito> getAll(){
        return service.getAllPrestiti();
    }

    @DeleteMapping("/{id}")
    public void deletePrestito(@PathVariable int id){
        service.deletePrestito(id);
    }
}
