package it.bitify.libreria.repository;

import it.bitify.libreria.entity.Corso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorsoRepo extends JpaRepository<Corso, Integer> {
}
