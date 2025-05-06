package it.bitify.libreria.service;

import it.bitify.libreria.entity.Libro;

import java.util.List;

public interface LibroService {

    Libro getLibroById(int id);

    void saveLibro(Libro libro);

    void updateLibro(Libro libro, int id);

    void deleteLibro(int id);

    List<Libro> getAllLibri();
}
