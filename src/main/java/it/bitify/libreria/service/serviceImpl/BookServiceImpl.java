package it.bitify.libreria.service.serviceImpl;

import it.bitify.libreria.model.entity.Book;
import it.bitify.libreria.exception.EntityNotFoundException;
import it.bitify.libreria.model.entity.Category;
import it.bitify.libreria.repository.BookRepo;
import it.bitify.libreria.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo repo;
    Logger logger = LogManager.getLogger(BookServiceImpl.class);

    @Override
    public Book findBookById(Long id) {
        return repo.findById(id).orElseThrow(() -> {
            EntityNotFoundException ex = new EntityNotFoundException("Libro non presente all'interno del database!");
            logger.error("Errore durante il recupero del libro con ID {}", id, ex);
            return ex;
        });
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "books", key = " 'allBooks' "),
            @CacheEvict(value = "categories", key = " 'categoriesBookCount' ")
    })
    public void saveBook(Book Book) {
        repo.save(Book);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "books", key = " 'allBooks' "),
            @CacheEvict(value = "categories", key = " 'categoriesBookCount' ")
    })
    public void updateBook(Book newBook) {
        if(repo.existsById(newBook.getBookId())){
            repo.save(newBook);
        }
        else { 
            EntityNotFoundException ex = new EntityNotFoundException("Libro non presente all'interno del database!");
            logger.error("Errore durante il recupero del libro con ID {}", newBook.getBookId(), ex);
            throw ex;
        }
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "books", key = " 'allBooks' "),
            @CacheEvict(value = "categories", key = " 'categoriesBookCount' ")
    })
    public void deleteBook(Long id) {
        if(repo.existsById(id)) {
            repo.deleteById(id);
        }
        else { 
            EntityNotFoundException ex = new EntityNotFoundException("Libro non presente all'interno del database!");
            logger.error("Errore durante il recupero del libro con ID {}", id, ex);
            throw ex;
        }
    }

    @Override
    @Cacheable(value = "books", key = " 'allBooks' ")
    public Page<Book> findAllBooks(Pageable pageable) {
        logger.debug("Lista di libri restituita");
        return repo.findAll(pageable);
    }

    @Override
    public Page<Book> findByTitleContaining(String infix, Pageable pageable) {
        return repo.findByTitleContaining(infix, pageable);
    }

    @Override
    public Page<Book> findByPublishYearGreaterThan(Integer year, Pageable pageable){
        return repo.findByYearGreaterThan(year, pageable);
    }

    @Override
    public Page<Book> findByCategory_Name(String name, Pageable pageable) {
        return repo.findByCategory_Name(name, pageable);
    }

    @Override
    public Page<Book> findBookWithoutLoan(Pageable pageable) {
        return repo.findBookWithoutLoan(pageable);
    }

    @Override
    public Page<String> findBookLoanInternval(LocalDate start, Pageable pageable) {
        return repo.findBookLoanInterval(start, pageable);
    }

    @Override
    public Page<Book> findBooksWithLoanAboveAverage(Pageable pageable) {
        return repo.findBooksWithLoanAboveAverage(pageable);
    }

    @Override
    public Page<Book> bookSuggestions(Category category, Long idStudent, Pageable pageable){
        return repo.findByCategoryOrderedByLoans(category, idStudent, pageable);
    }

}
