package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Corso;
import it.bitify.libreria.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

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
    public Page<Corso> getAll(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size,
    @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.getAllCorsi(pageable);
    }

    @DeleteMapping("/{id}")
    public void deleteCorso(@PathVariable Long id){
        service.deleteCorso(id);
    }
}
