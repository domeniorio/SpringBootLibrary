
package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Tessera;
import it.bitify.libreria.service.TesseraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


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
    Page<Tessera> getAllTessere(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size,
    @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.getAllTessere(pageable);
    }
}
