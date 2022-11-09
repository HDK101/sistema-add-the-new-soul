package br.edu.ifsp.addthenewsoul.domain.usecases.utils.exceptions;

public class EntityAlreadyExistsException extends RuntimeException{
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
