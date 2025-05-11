package it.bitify.libreria.service.serviceImpl;

import it.bitify.libreria.entity.Book;
import it.bitify.libreria.exception.EntityNotFoundException;
import it.bitify.libreria.repository.BookRepo;
import it.bitify.libreria.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo repo;

    @Override
    public Book getBookById(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
    }

    @Override
    public void saveBook(Book Book) {
        repo.save(Book);
    }

    @Override
    public void updateBook(Book newBook) {
        if(repo.existsById(newBook.getBookId())){
            repo.save(newBook);
        }
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");

    }

    @Override
    public void deleteBook(Long id) {
        if(repo.existsById(id)) {
            repo.deleteById(id);
        }
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Page<Book> findByTitleContaining(String infix, Pageable pageable) {
        return repo.findByTitleContaining(infix, pageable);
    }

    @Override
    public Page<Book> findByYearBetween(Integer startYear, Integer endYear, Pageable pageable) {
        return repo.findByYearBetween(startYear, endYear, pageable);
    }


}
