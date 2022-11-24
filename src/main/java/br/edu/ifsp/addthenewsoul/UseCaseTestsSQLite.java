package br.edu.ifsp.addthenewsoul;

import br.edu.ifsp.addthenewsoul.application.repository.database.SQLiteAssetDAO;
import br.edu.ifsp.addthenewsoul.application.repository.inMemory.InMemoryAssetDAO;
import br.edu.ifsp.addthenewsoul.application.repository.inMemory.InMemoryEmployeeDAO;
import br.edu.ifsp.addthenewsoul.application.repository.inMemory.InMemoryInventoryDAO;
import br.edu.ifsp.addthenewsoul.application.repository.inMemory.InMemoryLocationDAO;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.LocationStatus;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.*;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.*;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.*;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.*;
import br.edu.ifsp.addthenewsoul.domain.usecases.report.*;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Session;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UseCaseTestsSQLite {
    public static void main(String[] args) throws IOException {

	System.out.println("----- Apresentação Parcial -----");
        AssetDAO assetDAO = new SQLiteAssetDAO();

        AddAssetUseCase addAssetUseCase = new AddAssetUseCase(assetDAO);
        UpdateAssetUseCase updateAssetUseCase = new UpdateAssetUseCase(assetDAO);
        RemoveAssetUseCase removeAssetUseCase = new RemoveAssetUseCase(assetDAO);
        FindAssetUseCase findAssetUseCase = new FindAssetUseCase(assetDAO);

        Asset asset1 = Asset.builder().description("Cadeira").value(50.0).status(Status.NOT_VERIFIED).build();

        addAssetUseCase.add(asset1);

        System.out.println(
            findAssetUseCase.findOne(1).orElseThrow()
        );

        //System.out.println(assetDAO.findAll());

        /*
        asset1.setValue(496);
        asset1.setDescription("Teclado gamer");
        updateAssetUseCase.updateAsset(asset1);
        removeAssetUseCase.removeAsset(asset1);
         */

        System.out.println(assetDAO.findAll());
    }
}