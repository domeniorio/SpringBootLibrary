package it.bitify.libreria.service;

import it.bitify.libreria.entity.Libro;

import java.util.List;

public interface LibroService {

    Libro getLibroById(Long id);

    void saveLibro(Libro libro);

    void updateLibro(Libro libro);

    void deleteLibro(Long id);

    List<Libro> getAllLibri();
}
