package it.bitify.libreria.repository;

import it.bitify.libreria.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepo extends JpaRepository<Libro, Long> {
}
