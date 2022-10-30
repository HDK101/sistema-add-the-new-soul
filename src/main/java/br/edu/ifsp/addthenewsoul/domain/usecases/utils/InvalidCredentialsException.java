package br.edu.ifsp.addthenewsoul.domain.usecases.utils;

public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
