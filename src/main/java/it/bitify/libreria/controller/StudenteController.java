package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Studente;
import it.bitify.libreria.service.StudenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;


@Tag(name="Student", description = "Student management APIs")
@RestController
@RequestMapping("/api/v1/studente")
public class StudenteController {

    @Autowired
    StudenteService service;

    @Operation(
        summary = "Retrieve student by id",
        description = "Retrieve a student object by specifying its id. The response is a student object",
        tags = "get"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Found the student", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) }),
        @ApiResponse(responseCode = "400", description = "Id type mismatch", content = @Content),
        @ApiResponse(responseCode = "404", description = "Student not found", content = @Content) }
    )
    @GetMapping("/{id}")
    public Studente getStudenteById(@PathVariable Long id){
        return service.getStudenteById(id);
    }


    @Operation(
        summary = "Add student",
        description = "Add a student object to the database",
        tags = "post"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Student added to the database", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) }),
        @ApiResponse(responseCode = "500", description = "Data integrity violation", content = @Content)}
    )
    @PostMapping
    public void aggiungiStudente(@RequestBody Studente studente){
        service.saveStudente(studente);
    }


    @Operation(
        summary = "Modify student",
        description = "Modify a student object by specifying its id",
        tags = "put"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Student modified", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) }), 
        @ApiResponse(responseCode = "400", description = "Id type mismatch", content = @Content),
        @ApiResponse(responseCode = "404", description = "Student not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Data integrity violation", content = @Content) }
    )
    @PutMapping
    public void modificaStudente(@RequestBody Studente studente){
        service.updateStudente(studente);
    }


    @Operation(
        summary = "Retrieve all students",
        description = "Retrieve all student objects. The response is a list of student objects",
        tags = "get-all"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Retrieved all student", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) })}
    )
    @GetMapping
    public List<Studente> getAll(){
        return service.getAllStudenti();
    }


    @Operation(
        summary = "Delete student by id",
        description = "Delete a student object by specifying its id",
        tags = "delete"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "student deleted", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) }),
        @ApiResponse(responseCode = "400", description = "Id type mismatch", content = @Content),
        @ApiResponse(responseCode = "404", description = "Student not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Data integrity violation", content = @Content) }
    )
    @DeleteMapping("/{id}")
    public void deleteStudente(@PathVariable Long id){
        service.deleteStudente(id);
    }
}