package it.bitify.libreria.service.serviceImpl;

import it.bitify.libreria.model.entity.Card;
import it.bitify.libreria.exception.EntityNotFoundException;
import it.bitify.libreria.repository.CardRepo;
import it.bitify.libreria.service.CardService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepo repo;

    private Logger logger = LogManager.getLogger(CardServiceImpl.class);


    @Override
    public Card getCardById(Long id) {
        return repo.findById(id).orElseThrow(() -> {
            EntityNotFoundException ex = new EntityNotFoundException("Tessera non presente all'interno del database!");
            logger.error("Errore durante il recupero della tessera con ID {}", id, ex);
            return ex;
        });
    }

    @Override
    public void saveCard(Card Card) {
        repo.save(Card);
    }

    @Override
    public void updateCard(Card newCard) {
        if(repo.existsById(newCard.getIdCard())){
            repo.save(newCard);
        }
        else{
            EntityNotFoundException ex = new EntityNotFoundException("Tessera non presente all'interno del database!");
            logger.error("Errore durante il recupero della tessera con ID {}", newCard.getIdCard(), ex);
            throw ex;
        }
    }

    @Override
    public void deleteCard(Long id) {
        if(repo.existsById(id)){
            repo.deleteById(id);
        }
        else{
            EntityNotFoundException ex = new EntityNotFoundException("Tessera non presente all'interno del database!");
            logger.error("Errore durante il recupero della tessera con ID {}", id, ex);
            throw ex;
        }
    }

    @Override
    public Page<Card> getAllCards(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
