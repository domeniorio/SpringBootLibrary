package it.bitify.libreria.service;


import it.bitify.libreria.model.entity.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardService {

    Card getCardById(Long id);

    void saveCard(Card Card);

    void updateCard(Card Card);

    void deleteCard(Long id);

    Page<Card> getAllCards(Pageable pageable);

}
