package br.edu.ifsp.addthenewsoul.domain.usecases.local;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Local;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Notification;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validator;

public class ValidationOfLocalAttributes extends Validator<Local> {
    @Override
    public Notification isValid(Local local) {
        Notification notification = new Notification();

        if (local == null) {
            notification.addError("Local is null");
            return notification;
        }

        if (nullOrEmpty(local.getNumber().toString()))
            notification.addError("Number is null or empty");
        if (nullOrEmpty(local.getSection()))
            notification.addError("Section is null or empty");

        return notification;


    }
}
