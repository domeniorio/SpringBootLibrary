package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Prestito;
import it.bitify.libreria.service.PrestitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prestito")
public class PrestitoController {

    @Autowired
    PrestitoService service;

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
    public List<Prestito> getAll(){
        return service.getAllPrestiti();
    }

    @DeleteMapping("/{id}")
    public void deletePrestito(@PathVariable Long id){
        service.deletePrestito(id);
    }
}
