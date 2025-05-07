package it.bitify.libreria.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(RuntimeException exception){
        Map<String, Object> body = new HashMap<>();
        body.put("message", exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(DataIntegrityViolationException ex) {
        if (ex.getCause() != null){
            if(ex.getCause().getMessage().contains("FOREIGN KEY")) {
                return ResponseEntity.badRequest().body("Violazione di chiave esterna.");
            } else if(ex.getCause().getMessage().contains("PRIMARY KEY")) {
                return ResponseEntity.badRequest().body("Violazione di chiave primaria.");
            } else if(ex.getCause().getMessage().contains("CONSTRAINT")){
                return ResponseEntity.badRequest().body("Violazione di vincolo sui dati.");
            }
        }
        return ResponseEntity.badRequest().body("Violazione di integrità dei dati.");
    }

}
