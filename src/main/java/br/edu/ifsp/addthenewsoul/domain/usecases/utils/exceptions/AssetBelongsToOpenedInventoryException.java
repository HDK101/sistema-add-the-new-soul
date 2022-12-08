package br.edu.ifsp.addthenewsoul.domain.usecases.utils.exceptions;

public class AssetBelongsToOpenedInventoryException extends RuntimeException{
    public AssetBelongsToOpenedInventoryException(String message) {
        super(message);
    }
}
