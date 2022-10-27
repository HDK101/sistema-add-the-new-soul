package br.edu.ifsp.addthenewsoul.domain.usecases.location;

import java.util.Optional;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Local;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validator;

public class FindLocationUseCase {

    LocationDAO locationDAO;

    public FindLocationUseCase(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    public Optional<Local> findOne(Integer id) {
        if (Validator.nullOrEmpty(id.toString()))
            throw new IllegalArgumentException("Registration number can not be null or empty");
        return locationDAO.findById(id);
    }
}
