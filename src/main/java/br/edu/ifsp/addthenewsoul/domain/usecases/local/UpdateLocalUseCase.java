package br.edu.ifsp.addthenewsoul.domain.usecases.local;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Local;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Notification;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validator;

public class UpdateLocalUseCase {

    private LocalDAO localDAO;

    public UpdateLocalUseCase(LocalDAO localDAO) {
        this.localDAO = localDAO;
    }

    public boolean update(Local local) {
        Validator<Local> validator = new ValidationOfLocalAttributes();
        Notification notification = validator.isValid(local);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        final Integer id = local.getId();
        if (localDAO.findById(id).isEmpty())
            throw new IllegalArgumentException("Local not found.");

        return localDAO.update(local);
    }
}
