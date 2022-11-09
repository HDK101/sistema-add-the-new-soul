package br.edu.ifsp.addthenewsoul.domain.usecases.utils.exceptions;

public class AssetDependencyNotFoundException extends RuntimeException{
    public AssetDependencyNotFoundException(String message) {
        super(message);
    }
}