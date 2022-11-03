package br.edu.ifsp.addthenewsoul.domain.usecases.location;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Notification;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validator;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;



public class AddLocationUseCase {

    private LocationDAO locationDAO;

    public AddLocationUseCase(LocationDAO localDAO) {
        this.locationDAO = localDAO;
    }

    public Integer save (Location location) {
        Validator<Location> validator = new ValidationOfLocationAttributes();
        Notification notification = validator.isValid(location);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        if (locationDAO.findByLocation(location.getNumber(), location.getSection()).isPresent())
            throw new IllegalArgumentException("This location is already in use");

        return locationDAO.add(location);
    }
}
