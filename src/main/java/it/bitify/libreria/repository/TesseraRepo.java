package it.bitify.libreria.repository;

import it.bitify.libreria.entity.Tessera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TesseraRepo extends JpaRepository<Tessera, Long> {
}
