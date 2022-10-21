package br.edu.ifsp.addthenewsoul.domain.usecases.good;

import br.edu.ifsp.addthenewsoul.domain.entities.good.Good;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Notification;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validator;

public class ValidationOfGoodValues extends Validator<Good> {
    @Override
    public Notification isValid(Good good) {
        Notification notification = new Notification();

        if (good == null) {
            notification.addError("Good is null");
            return notification;
        }

        if (nullOrEmpty(good.getDamage()))
            notification.addError("Damage is null or empty");
        if (nullOrEmpty(String.valueOf(good.getId())))
            notification.addError("Id is null or empty");
        if (nullOrEmpty(good.getDescription()))
            notification.addError("Description is null or empty");
        if (nullOrEmpty(String.valueOf(good.getEmployeeInCharge())))
            notification.addError("Employee in charge of good is null or empty");
        if (nullOrEmpty(String.valueOf(good.getValue())))
            notification.addError("Value is null or empty");

        return notification;
    }
}
