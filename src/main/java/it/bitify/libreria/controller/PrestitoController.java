package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Prestito;
import it.bitify.libreria.entity.Studente;
import it.bitify.libreria.service.PrestitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name="Loan", description = "Loan management APIs")
@RestController
@RequestMapping("/api/v1/prestito")
public class PrestitoController {

    @Autowired
    PrestitoService service;

    @Operation(
        summary = "Retrieve Loan by id",
        description = "Retrieve a Loan object by specifying its id. The response is a Loan object",
        tags = "get"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Found the Loan", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) }),
        @ApiResponse(responseCode = "400", description = "Id type mismatch", content = @Content),
        @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content) }
    )
    @GetMapping("/{id}")
    public Prestito getPrestitoById(@PathVariable Long id){
        return service.getPrestitoById(id);
    }


    @Operation(
        summary = "Add Loan",
        description = "Add a Loan object to the database",
        tags = "post"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Loan added to the database", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) }),
        @ApiResponse(responseCode = "500", description = "Data integrity violation", content = @Content)}
    )
    @PostMapping
    public void aggiungiPrestito(@RequestBody Prestito Prestito){
        service.savePrestito(Prestito);
    }


    @Operation(
        summary = "Modify Loan",
        description = "Modify a Loan object by specifying its id",
        tags = "put"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Loan modified", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) }), 
        @ApiResponse(responseCode = "400", description = "Id type mismatch", content = @Content),
        @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Data integrity violation", content = @Content) }
    )
    @PutMapping
    public void modificaPrestito(@RequestBody Prestito Prestito) {
        service.updatePrestito(Prestito);
    }


    @Operation(
        summary = "Retrieve all students",
        description = "Retrieve all Loan objects. The response is a list of Loan objects",
        tags = "get-all"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Retrieved all Loan", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) })}
    )
    @GetMapping
    public List<Prestito> getAll(){
        return service.getAllPrestiti();
    }


    @Operation(
        summary = "Delete Loan by id",
        description = "Delete a Loan object by specifying its id",
        tags = "delete"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Loan deleted", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Studente.class)) }),
        @ApiResponse(responseCode = "400", description = "Id type mismatch", content = @Content),
        @ApiResponse(responseCode = "404", description = "Loan not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Data integrity violation", content = @Content) }
    )
    @DeleteMapping("/{id}")
    public void deletePrestito(@PathVariable Long id){
        service.deletePrestito(id);
    }
}
