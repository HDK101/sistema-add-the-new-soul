package br.edu.ifsp.addthenewsoul.domain.usecases.local;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Local;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Notification;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validator;

public class AddLocalUseCase {

    private LocalDAO localDAO;

    public AddLocalUseCase(LocalDAO localDAO) {
        this.localDAO = localDAO;
    }

    public Integer save (Local local) {
        Validator<Local> validator = new ValidationOfLocalAttributes();
        Notification notification = validator.isValid(local);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        if (localDAO.findByLocation(local.getNumber(), local.getSection()).isPresent())
            throw new IllegalArgumentException("This local is already in use");

        return localDAO.add(local);
    }
}
