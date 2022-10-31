package br.edu.ifsp.addthenewsoul.domain.usecases.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Validator<T> {

    public abstract Notification isValid (T type);

    public static boolean nullOrEmpty(String string){
        return string == null || string.isEmpty();
    }

    public static boolean isValid (String initialDate, String endDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate d = LocalDate.parse(initialDate, formatter);
            LocalDate d_2 = LocalDate.parse(endDate, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean checkIfDateHasPassed (String initialDate, String endDate) {
        LocalDate current_date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate current_dateFormatedIniDate = LocalDate.parse(initialDate, formatter);
        LocalDate current_dateFormatedEndDate = LocalDate.parse(endDate, formatter);
        return current_dateFormatedIniDate.compareTo(current_date) != 0 &&
                current_dateFormatedEndDate.compareTo(current_date) != 0;
    }

}
