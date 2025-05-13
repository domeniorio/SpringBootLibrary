
package it.bitify.libreria.controller;

import it.bitify.libreria.model.entity.Card;
import it.bitify.libreria.service.CardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/card")
public class CardController {

    @Autowired
    private CardService service;

    @GetMapping("/{id}")
    Card getCardById(@PathVariable Long id){
        return service.getCardById(id);
    }

    @PostMapping
    void addCard(@RequestBody Card Card){
        service.saveCard(Card);
    }

    @PutMapping
    void editCard(@RequestBody Card Card){
        service.updateCard(Card);
    }

    @DeleteMapping("/{id}")
    void removeCard(@PathVariable Long id){
        service.deleteCard(id);
    }

    @GetMapping
    Page<Card> getAllCards(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size,
    @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.getAllCards(pageable);
    }
}
