package it.bitify.libreria.repository;

import it.bitify.libreria.entity.Studente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudenteRepo extends JpaRepository<Studente, Integer> {
}
