package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Book;
import it.bitify.libreria.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    BookService service;

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id){
        return service.getBookById(id);
    }

    @PostMapping
    public void addBook(@RequestBody Book Book){
        service.saveBook(Book);
    }

    @PutMapping
    public void editBook(@RequestBody Book Book){
        service.updateBook(Book);
    }

    @GetMapping
    public Page<Book> getAll(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size,
    @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.getAllBooks(pageable);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        service.deleteBook(id);
    }

    @GetMapping("/by-name")
    public Page<Book> findByTitleContaining(@RequestParam String query,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size,
    @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.findByTitleContaining(query, pageable);
    }

    @GetMapping("/by-year-greater-than")
    public Page<Book> findByPublishYearGreaterThan(@RequestParam(defaultValue = "0") int year,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size,
    @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.findByPublishYearGreaterThan(year, pageable);
    }

    @GetMapping("/by-category-name")
    public Page<Book> findByCategoryName(@RequestParam String name,
            @RequestParam(defaultValue = "0") int year,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.findByCategory_Name(name, pageable);
    }
}
