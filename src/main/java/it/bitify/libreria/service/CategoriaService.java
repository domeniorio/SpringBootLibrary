package it.bitify.libreria.service;

import it.bitify.libreria.entity.Categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriaService {

    Categoria getCategoriaById(Long id);

    void saveCategoria(Categoria categoria);

    void updateCategoria(Categoria categoria);

    void deleteCategoria(Long id);

    Page<Categoria> getAllCategorie(Pageable pageable);
}
