package br.edu.ifsp.addthenewsoul.domain.usecases.bem;

import br.edu.ifsp.addthenewsoul.domain.entities.bem.Bem;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Notification;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validador;

public class BemCreateUseCase {

    private BemDAO bemDAO;

    public BemCreateUseCase(BemDAO bemDAO) {
        this.bemDAO = bemDAO;
    }

    public Integer save (Bem bem) {
        Validador<Bem> validador = new ValidationOfAssetValues();
        Notification notification = validador.validade(bem);

        if (notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer code = bem.getCodigo();
        if (bemDAO.findByCode(code).isPresent())
            throw new IllegalArgumentException("This BEM is already in use");

        return bemDAO.create(bem);
    }
}
