package br.edu.ifsp.addthenewsoul.application.controller.validators;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Notification;

public class LocationUIValidator {

    public Notification isValid(String txtNumber, String txtSection) {
        Notification notification = new Notification();

        if (txtNumber.equals(""))
            notification.addError("Number não pode ser vazio");
        if (txtSection.equals(""))
            notification.addError("Section não pode ser vazia");
        try {
            Integer.valueOf(txtNumber);
        } catch (Exception e) {
            notification.addError("Number deve ser um inteiro");
        }

        return notification;
    }
}
