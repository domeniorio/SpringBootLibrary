package it.bitify.libreria.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException() { super(); }
    public EntityNotFoundException(String msg) { super(msg); }
}
