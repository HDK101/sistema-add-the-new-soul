package br.edu.ifsp.addthenewsoul.domain.usecases.asset;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Notification;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Validator;

public class ValidationOfAssetValues extends Validator<Asset> {
    @Override
    public Notification isValid(Asset asset) {
        Notification notification = new Notification();

        if (asset == null) {
            notification.addError("asset is null");
            return notification;
        }

        if (nullOrEmpty(asset.getDamage()))
            notification.addError("Damage is null or empty");
        if (nullOrEmpty(String.valueOf(asset.getId())))
            notification.addError("Id is null or empty");
        if (nullOrEmpty(asset.getDescription()))
            notification.addError("Description is null or empty");
        if (nullOrEmpty(String.valueOf(asset.getEmployeeInCharge())))
            notification.addError("Employee in charge of asset is null or empty");
        if (nullOrEmpty(String.valueOf(asset.getValue())))
            notification.addError("Value is null or empty");

        return notification;
    }
}
