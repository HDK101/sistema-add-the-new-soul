package br.edu.ifsp.addthenewsoul.domain.usecases.bem;

import br.edu.ifsp.addthenewsoul.domain.entities.bem.Bem;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Notification;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validador;

public class ValidationOfAssetValues extends Validador<Bem> {
    @Override
    public Notification validade(Bem bem) {
        Notification notification = new Notification();

        if (bem == null) {
            notification.addError("Bem is null");
            return notification;
        }

        if (nullOrEmpty(bem.getAvaria()))
            notification.addError("Avaria is null or empty");
        if (nullOrEmpty(String.valueOf(bem.getCodigo())))
            notification.addError("Code is null or empty");
        if (nullOrEmpty(bem.getDescricao()))
            notification.addError("Description is null or empty");
        if (nullOrEmpty(String.valueOf(bem.getResponsavel())))
            notification.addError("Responsible is null or empty");
        if (nullOrEmpty(String.valueOf(bem.getValor())))
            notification.addError("Value is null or empty");

        return notification;
    }
}
