package it.bitify.libreria.repository;

import it.bitify.libreria.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepo extends JpaRepository<Card, Long> {
}
