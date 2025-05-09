package it.bitify.libreria.repository;

import it.bitify.libreria.entity.Studente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudenteRepo extends JpaRepository<Studente, Long> {
    Page<Studente> findByNomeAndCognome(String nome, String cognome, Pageable pageable);
}
