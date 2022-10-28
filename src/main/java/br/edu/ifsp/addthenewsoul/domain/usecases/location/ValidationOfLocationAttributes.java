package br.edu.ifsp.addthenewsoul.domain.usecases.location;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Notification;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validator;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;


public class ValidationOfLocationAttributes extends Validator<Location> {
    @Override
    public Notification isValid(Location location) {
        Notification notification = new Notification();

        if (location == null) {
            notification.addError("Location is null");
            return notification;
        }

        if (nullOrEmpty(location.getNumber().toString()))
            notification.addError("Number is null or empty");
        if (nullOrEmpty(location.getSection()))
            notification.addError("Section is null or empty");

        return notification;


    }
}
