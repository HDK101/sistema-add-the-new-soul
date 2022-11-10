package br.edu.ifsp.addthenewsoul.domain.usecases.utils.exceptions;

public class EmployeeBelongsToOpenedInventoryException extends RuntimeException{
    public EmployeeBelongsToOpenedInventoryException(String message) {
        super(message);
    }
}
