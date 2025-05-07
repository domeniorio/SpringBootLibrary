package it.bitify.libreria.repository;

import it.bitify.libreria.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {
}
