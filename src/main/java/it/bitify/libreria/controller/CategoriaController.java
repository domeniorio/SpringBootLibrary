package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Categoria;
import it.bitify.libreria.entity.Studente;
import it.bitify.libreria.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name="Category", description = "Category management APIs")
@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaController {
    
    @Autowired
    private CategoriaService service;


   @Operation(
        summary = "Retrieve Category by id",
        description = "Retrieve a Category object by specifying its id. The response is a Category object",
        tags = "get"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Found the Category", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) }),
        @ApiResponse(responseCode = "400", description = "Id type mismatch", content = @Content),
        @ApiResponse(responseCode = "404", description = "Category not found", content = @Content) }
    )
    @GetMapping("/{id}")
    Categoria getCategoriaById(@PathVariable Long id){
        return service.getCategoriaById(id);
    }



    @Operation(
        summary = "Add Category",
        description = "Add a Category object to the database",
        tags = "post"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Category added to the database", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) }),
        @ApiResponse(responseCode = "500", description = "Data integrity violation", content = @Content)}
    )
    @PostMapping
    void aggiungiCategoria(@RequestBody Categoria Categoria){
        service.saveCategoria(Categoria);
    }


    @Operation(
        summary = "Modify Category",
        description = "Modify a Category object by specifying its id",
        tags = "put"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Category modified", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) }), 
        @ApiResponse(responseCode = "400", description = "Id type mismatch", content = @Content),
        @ApiResponse(responseCode = "404", description = "Category not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Data integrity violation", content = @Content) }
    )
    @PutMapping
    void modificaCategoria(@RequestBody Categoria categoria){
        service.updateCategoria(categoria);
    }


    @Operation(
        summary = "Delete Category by id",
        description = "Delete a Category object by specifying its id",
        tags = "delete"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Category deleted", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) }),
        @ApiResponse(responseCode = "400", description = "Id type mismatch", content = @Content),
        @ApiResponse(responseCode = "404", description = "Category not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Data integrity violation", content = @Content) }
    )
    @DeleteMapping("/{id}")
    void rimuoviCategoria(@PathVariable Long id){
        service.deleteCategoria(id);
    }


    @Operation(
        summary = "Retrieve all students",
        description = "Retrieve all Category objects. The response is a list of Category objects",
        tags = "get-all"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Retrieved all Category", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) })}
    )
    @GetMapping
    List<Categoria> getAllCategorie(){
        return service.getAllCategorie();
    }
}
