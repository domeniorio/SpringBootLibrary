package it.bitify.libreria.repository;

import it.bitify.libreria.entity.Prestito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestitoRepo extends JpaRepository<Prestito, Integer> {
}
