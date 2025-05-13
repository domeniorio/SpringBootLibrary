package it.bitify.libreria.service.serviceImpl;

import it.bitify.libreria.model.entity.Card;
import it.bitify.libreria.exception.EntityNotFoundException;
import it.bitify.libreria.repository.CardRepo;
import it.bitify.libreria.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepo repo;

    @Override
    public Card getCardById(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
    }

    @Override
    public void saveCard(Card Card) {
        repo.save(Card);
    }

    @Override
    public void updateCard(Card newCard) {
        if (repo.existsById(newCard.getIdCard())){
            repo.save(newCard);
        }
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public void deleteCard(Long id) {
        if(repo.existsById(id)){
            repo.deleteById(id);
        }
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public Page<Card> getAllCards(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
