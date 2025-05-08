package it.bitify.libreria.service;

import it.bitify.libreria.entity.Categoria;

import java.util.List;

public interface CategoriaService {

    Categoria getCategoriaById(Long id);

    void saveCategoria(Categoria categoria);

    void updateCategoria(Categoria categoria);

    void deleteCategoria(Long id);

    List<Categoria> getAllCategorie();
}
