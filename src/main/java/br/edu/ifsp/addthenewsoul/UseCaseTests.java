package br.edu.ifsp.addthenewsoul;

import br.edu.ifsp.addthenewsoul.application.repository.inMemory.InMemoryAssetDAO;
import br.edu.ifsp.addthenewsoul.application.repository.inMemory.InMemoryEmployeeDAO;
import br.edu.ifsp.addthenewsoul.application.repository.inMemory.InMemoryInventoryDAO;
import br.edu.ifsp.addthenewsoul.application.repository.inMemory.InMemoryLocationDAO;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.*;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.*;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.InventoryDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.AddLocationUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.LocationDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Session;

public class UseCaseTests {
    public static void main(String[] args) {
        AssetDAO assetDAO = new InMemoryAssetDAO();
        EmployeeDAO employeeDAO = new InMemoryEmployeeDAO();
        LocationDAO locationDAO = new InMemoryLocationDAO();
        InventoryDAO inventoryDAO = new InMemoryInventoryDAO();

        AddAssetUseCase addAssetUseCase = new AddAssetUseCase(assetDAO);
        UpdateAssetUseCase updateAssetUseCase = new UpdateAssetUseCase(assetDAO);
        RemoveAssetUseCase removeAssetUseCase = new RemoveAssetUseCase(assetDAO);
        FindAssetUseCase findAssetUseCase = new FindAssetUseCase(assetDAO);

        AddLocationUseCase addLocationUseCase = new AddLocationUseCase(locationDAO);

        AddEmployeeUseCase addEmployeeUseCase = new AddEmployeeUseCase(employeeDAO);
        UpdateEmployeeUseCase updateEmployeeUseCase = new UpdateEmployeeUseCase(employeeDAO);
        RemoveEmployeeUseCase removeEmployeeUseCase = new RemoveEmployeeUseCase(employeeDAO);
        LoginEmployeeUseCase loginEmployeeUseCase = new LoginEmployeeUseCase(employeeDAO);
        NominateEmployeeInChargeUseCase nominateEmployeeInChargeUseCase = new NominateEmployeeInChargeUseCase(employeeDAO);


        Employee employee1 = new Employee("Walter", "R12345", "senha123", "noname@email.com", "(18) 99999-9999", Role.EXECUTOR);
        Employee employee2 = new Employee("Eisen", "R12346", "senha456", "eisen@email.com", "(16) 98888-8888", Role.EXECUTOR);
        Employee employee3 = new Employee("Joseph", "R12347", "senha789", "joseph@email.com", "(19) 97777-7777", Role.INVENTORY_MANAGER);
        addEmployeeUseCase.add(employee1);
        addEmployeeUseCase.add(employee2);
        addEmployeeUseCase.add(employee3);


        System.out.println("----- ORIGINAL LIST OF EMPLOYEES -----");


        System.out.println(employeeDAO.findAll());


        System.out.println("----- UPDATED LIST OF EMPLOYEES -----");

        employee1.setEmail("walter@gmail.com");
        updateEmployeeUseCase.update(employee1);
        removeEmployeeUseCase.remove("R12346");

        System.out.println(employeeDAO.findAll());


        System.out.println("----- EMPLOYEE LOGIN -----");


        Employee employee = loginEmployeeUseCase.login("walter@gmail.com", "senha123");
        System.out.println(employee);
        System.out.println(Session.getInstance().getLoggedUser());
        System.out.println(Session.getInstance().getLoggedUser() == employee);


        System.out.println("----- ORIGINAL LIST OF ASSETS -----");


        Asset asset1 = new Asset(1,"Cadeira", 50.0, "Nenhum");
        Asset asset2 = new Asset(2,"Mesa", 200.0, "Nenhum");
        Asset asset3 = new Asset(3,"Computador Desktop", 2000.0, "Nenhum");

        addAssetUseCase.add(asset1);
        addAssetUseCase.add(asset2);
        addAssetUseCase.add(asset3);

        System.out.println(assetDAO.findAll());


        System.out.println("----- UPDATED LIST OF ASSETS -----");


        asset2.setValue(496);
        asset2.setDescription("Teclado gamer");
        updateAssetUseCase.updateAsset(asset2);
        removeAssetUseCase.removeAsset(asset1);

        System.out.println(assetDAO.findAll());


        System.out.println("----- ORIGINAL LIST OF LOCATIONS -----");


        Location location1 = new Location(1, 10, "Seção A");
        Location location2 = new Location(2, 20, "Seção B");
        Location location3 = new Location(3, 30, "Seção C");
        addLocationUseCase.add(location1);
        addLocationUseCase.add(location2);
        addLocationUseCase.add(location3);


        System.out.println("----- ASSETS UPDATED WITH EMPLOYEES IN CHARGE AND LOCATIONS -----");

        nominateEmployeeInChargeUseCase.nominateEmployeeInCharge(employee1, asset3, location1);
        nominateEmployeeInChargeUseCase.nominateEmployeeInCharge(employee2, asset2, location2);
        updateAssetUseCase.updateAsset(asset2);
        updateAssetUseCase.updateAsset(asset3);

        System.out.println(findAssetUseCase.findOne(2));
        System.out.println(findAssetUseCase.findOne(3));


        System.out.println("----- EMPLOYEE LOGOUT -----");


        LogoutEmployeeUseCase logoutEmployeeUseCase = new LogoutEmployeeUseCase();
        logoutEmployeeUseCase.logout();

        System.out.println(Session.getInstance().getLoggedUser() == null);


    }
}
