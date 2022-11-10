package br.edu.ifsp.addthenewsoul.domain.usecases.location;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validator;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

public class FindLocationUseCase {
    private LocationDAO locationDAO;

    public FindLocationUseCase(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    public Optional<Location> findOne (Integer  id) {
        if (Validator.nullOrEmpty(String.valueOf(id)))
            throw new EntityNotFoundException("Location not found. ID cannot be null or empty.");
        return locationDAO.findById(id);
    }

    public List<Location> findAll () {
        return locationDAO.findAll();
    }
}
