package br.edu.ifsp.addthenewsoul.domain.usecases.location;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Notification;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validator;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;


public class UpdateLocationUseCase {

    private LocationDAO locationDAO;

    public UpdateLocationUseCase(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    public boolean update(Location location) {
        Validator<Location> validator = new ValidationOfLocationAttributes();
        Notification notification = validator.isValid(location);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        final Integer id = location.getId();
        if (locationDAO.findById(id).isEmpty())
            throw new IllegalArgumentException("Location not found.");

        return locationDAO.update(location);
    }
}
