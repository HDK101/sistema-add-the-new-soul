package br.edu.ifsp.addthenewsoul.domain.usecases.utils.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message) {
        super(message);
    }
}
