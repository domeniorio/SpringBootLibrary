package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Libro;
import it.bitify.libreria.entity.Studente;
import it.bitify.libreria.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name="Book", description = "Book management APIs")
@RestController
@RequestMapping("/api/v1/libro")
public class LibroController {

    @Autowired
    LibroService service;


    @Operation(
        summary = "Retrieve Book by id",
        description = "Retrieve a Book object by specifying its id. The response is a Book object",
        tags = "get"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Found the Book", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) }),
        @ApiResponse(responseCode = "400", description = "Id type mismatch", content = @Content),
        @ApiResponse(responseCode = "404", description = "Book not found", content = @Content) }
    )
    @GetMapping("/{id}")
    public Libro getLibroById(@PathVariable Long id){
        return service.getLibroById(id);
    }


    @Operation(
        summary = "Add Book",
        description = "Add a Book object to the database",
        tags = "post"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Book added to the database", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) }),
        @ApiResponse(responseCode = "500", description = "Data integrity violation", content = @Content)}
    )
    @PostMapping
    public void aggiungiLibro(@RequestBody Libro Libro){
        service.saveLibro(Libro);
    }


    @Operation(
        summary = "Modify Book",
        description = "Modify a Book object by specifying its id",
        tags = "put"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Book modified", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) }), 
        @ApiResponse(responseCode = "400", description = "Id type mismatch", content = @Content),
        @ApiResponse(responseCode = "404", description = "Book not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Data integrity violation", content = @Content) }
    )
    @PutMapping
    public void modificaLibro(@RequestBody Libro Libro){
        service.updateLibro(Libro);
    }


    @Operation(
        summary = "Retrieve all students",
        description = "Retrieve all Book objects. The response is a list of Book objects",
        tags = "get-all"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Retrieved all Book", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) })}
    )
    @GetMapping
    public List<Libro> getAll(){
        return service.getAllLibri();
    }

    @Operation(
        summary = "Delete Book by id",
        description = "Delete a Book object by specifying its id",
        tags = "delete"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Book deleted", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) }),
        @ApiResponse(responseCode = "400", description = "Id type mismatch", content = @Content),
        @ApiResponse(responseCode = "404", description = "Book not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Data integrity violation", content = @Content) }
    )
    @DeleteMapping("/{id}")
    public void deleteLibro(@PathVariable Long id){
        service.deleteLibro(id);
    }
}
