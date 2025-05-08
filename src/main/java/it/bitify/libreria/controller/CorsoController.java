package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Corso;
import it.bitify.libreria.entity.Studente;
import it.bitify.libreria.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name="Course", description = "Course management APIs")
@RestController
@RequestMapping("/api/v1/corso")
public class CorsoController {

    @Autowired
    CorsoService service;

    @Operation(
        summary = "Retrieve Course by id",
        description = "Retrieve a Course object by specifying its id. The response is a Course object",
        tags = "get"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Found the Course", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) }),
        @ApiResponse(responseCode = "400", description = "Id type mismatch", content = @Content),
        @ApiResponse(responseCode = "404", description = "Course not found", content = @Content) }
    )
    @GetMapping("/{id}")
    public Corso getCorsoById(@PathVariable Long id){
        return service.getCorsoById(id);
    }


    @Operation(
        summary = "Add Course",
        description = "Add a Course object to the database",
        tags = "post"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Course added to the database", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) }),
        @ApiResponse(responseCode = "500", description = "Data integrity violation", content = @Content)}
    )
    @PostMapping
    public void aggiungiCorso(@RequestBody Corso corso){
        service.saveCorso(corso);
    }


    @Operation(
        summary = "Modify Course",
        description = "Modify a Course object by specifying its id",
        tags = "put"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Course modified", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) }), 
        @ApiResponse(responseCode = "400", description = "Id type mismatch", content = @Content),
        @ApiResponse(responseCode = "404", description = "Course not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Data integrity violation", content = @Content) }
    )
    @PutMapping
    public void modificaCorso(@RequestBody Corso corso){
        service.updateCorso(corso);
    }


    @Operation(
        summary = "Retrieve all students",
        description = "Retrieve all Course objects. The response is a list of Course objects",
        tags = "get-all"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Retrieved all Course", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) })}
    )
    @GetMapping
    public List<Corso> getAll(){
        return service.getAllCorsi();
    }


    @Operation(
        summary = "Delete Course by id",
        description = "Delete a Course object by specifying its id",
        tags = "delete"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Course deleted", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) }),
        @ApiResponse(responseCode = "400", description = "Id type mismatch", content = @Content),
        @ApiResponse(responseCode = "404", description = "Course not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Data integrity violation", content = @Content) }
    )
    @DeleteMapping("/{id}")
    public void deleteCorso(@PathVariable Long id){
        service.deleteCorso(id);
    }
}
