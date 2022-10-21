package br.edu.ifsp.addthenewsoul.domain.usecases.good;

import br.edu.ifsp.addthenewsoul.domain.entities.good.Good;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Notification;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validator;

public class AddGoodUseCase {

    private GoodDAO goodDAO;

    public AddGoodUseCase(GoodDAO goodDAO) {
        this.goodDAO = goodDAO;
    }

    public Integer save (Good good) {
        Validator<Good> validator = new ValidationOfGoodValues();
        Notification notification = validator.isValid(good);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer code = good.getId();
        if (goodDAO.findById(code).isPresent())
            throw new IllegalArgumentException("This id is already in use");

        return goodDAO.add(good);
    }
}
