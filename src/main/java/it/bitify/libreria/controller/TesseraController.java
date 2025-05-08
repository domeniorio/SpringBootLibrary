
package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Tessera;
import it.bitify.libreria.service.TesseraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tessera")
public class TesseraController {

    @Autowired
    private TesseraService service;

    @GetMapping("/{id}")
    Tessera getTesseraById(@PathVariable Long id){
        return service.getTesseraById(id);
    }

    @PostMapping
    void aggiungiTessera(@RequestBody Tessera tessera){
        service.saveTessera(tessera);
    }

    @PutMapping
    void modificaTessera(@RequestBody Tessera tessera){
        service.updateTessera(tessera);
    }

    @DeleteMapping("/{id}")
    void rimuoviTessera(@PathVariable Long id){
        service.deletetessera(id);
    }

    @GetMapping
    List<Tessera> getAllTessere(){
        return service.getAllTessere();
    }
}
