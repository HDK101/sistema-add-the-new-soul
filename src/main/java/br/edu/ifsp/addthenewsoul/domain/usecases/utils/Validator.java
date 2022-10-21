package br.edu.ifsp.addthenewsoul.domain.usecases.utils;

public abstract class Validator<T> {

    public abstract Notification isValid (T type);

    public static boolean nullOrEmpty(String string){
        return string == null || string.isEmpty();
    }

}
