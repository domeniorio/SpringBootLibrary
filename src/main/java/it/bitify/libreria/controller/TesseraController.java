
package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Tessera;
import it.bitify.libreria.service.TesseraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


import java.util.List;

@Tag(name = "Card", description = "Card management APIs")
@RestController
@RequestMapping("/api/v1/tessera")
public class TesseraController {

    @Autowired
    private TesseraService service;

    @Operation(
        summary = "Retrieve card by id",
        description = "Retrieve a card object by specifying its id. The response is a card object",
        tags = "get"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Found the card", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Tessera.class)) }),
        @ApiResponse(responseCode = "400", description = "Id type mismatch", content = @Content),
        @ApiResponse(responseCode = "404", description = "Card not found", content = @Content) }
    )
    @GetMapping("/{id}")
    Tessera getTesseraById(@PathVariable Long id){
        return service.getTesseraById(id);
    }


    @Operation(
        summary = "Add card",
        description = "Add a card object to the database",
        tags = "post"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Card added to the database", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Tessera.class)) }),
        @ApiResponse(responseCode = "500", description = "Data integrity violation", 
            content = @Content)}
    )
    @PostMapping
    void aggiungiTessera(@RequestBody Tessera tessera){
        service.saveTessera(tessera);
    }


    @Operation(
        summary = "Modify card",
        description = "Modify a card object by specifying its id",
        tags = "put"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Card modified", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Tessera.class)) }),

        @ApiResponse(responseCode = "400", description = "Id type mismatch", content = @Content),
        @ApiResponse(responseCode = "404", description = "Card not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Data integrity violation", content = @Content) }
    )
    @PutMapping
    void modificaTessera(@RequestBody Tessera tessera){
        service.updateTessera(tessera);
    }


    @Operation(
        summary = "Delete by id",
        description = "Delete a card object by specifying its id",
        tags = "delete"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Card deleted", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Tessera.class)) }),
        @ApiResponse(responseCode = "400", description = "Id type mismatch", content = @Content),
        @ApiResponse(responseCode = "404", description = "card not found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Data integrity violation", content = @Content) }
    )
    @DeleteMapping("/{id}")
    void rimuoviTessera(@PathVariable Long id){
        service.deletetessera(id);
    }


    @Operation(
        summary = "Retrieve all cards",
        description = "Retrieve all card objects. The response is a list of card objects",
        tags = "get-all"
    )
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Retrieved all cards", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Tessera.class)) })}
    )
    @GetMapping
    List<Tessera> getAllTessere(){
        return service.getAllTessere();
    }
}
