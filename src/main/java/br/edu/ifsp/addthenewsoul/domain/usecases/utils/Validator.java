package br.edu.ifsp.addthenewsoul.domain.usecases.utils;

public abstract class Validator<T> {

    public abstract Notification validity (T type);

    public static boolean nullOrEmpty(String string){
        return string == null || string.isEmpty();
    }

}
