package br.edu.ifsp.addthenewsoul.application;

import br.edu.ifsp.addthenewsoul.application.repository.database.SQLiteAssetDAO;
import br.edu.ifsp.addthenewsoul.application.repository.database.SQLiteInventoryDAO;
import br.edu.ifsp.addthenewsoul.application.repository.database.SQLiteLocationDAO;
import br.edu.ifsp.addthenewsoul.application.view.WindowLoader;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.LoginEmployeeUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.InventoryDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.LocationDAO;

public class Main {

    public static LoginEmployeeUseCase loginEmployeeUseCase;

    public static void main(String[] args) {
        configureInjection();
        WindowLoader.main(args);
    }

    private static void configureInjection() {
        /*EmployeeDAO employeeDAO = new SQLiteEmployeeDAO();
        loginEmployeeUseCase = new LoginEmployeeUseCase(employeeDAO);
         */

        AssetDAO assetDAO = new SQLiteAssetDAO();
        LocationDAO locationDAO = new SQLiteLocationDAO();
        InventoryDAO inventoryDAO = new SQLiteInventoryDAO();

    }
}
