package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Tessera;
import it.bitify.libreria.service.TesseraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tessera")
public class TesseraController {

    @Autowired
    private TesseraService service;

    @GetMapping("/{id}")
    Tessera getTesseraById(@PathVariable int id){
        return service.getTesseraById(id);
    }

    @PostMapping("/aggiungi")
    void aggiungiTessera(@RequestBody Tessera tessera){
        service.saveTessera(tessera);
    }

    @PutMapping("/modifica/{id}")
    void modificaTessera(@RequestBody Tessera tessera, @PathVariable int id){
        service.updateTessera(tessera,id);
    }

    @DeleteMapping("/{id}")
    void rimuoviTessera(@PathVariable int id){
        service.deletetessera(id);
    }

    @GetMapping("/")
    List<Tessera> getAllTessere(){
        return service.getAllTessere();
    }
}
