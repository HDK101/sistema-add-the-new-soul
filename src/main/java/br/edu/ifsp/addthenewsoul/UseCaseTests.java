package br.edu.ifsp.addthenewsoul;

import br.edu.ifsp.addthenewsoul.application.repository.inMemory.InMemoryAssetDAO;
import br.edu.ifsp.addthenewsoul.application.repository.inMemory.InMemoryEmployeeDAO;
import br.edu.ifsp.addthenewsoul.application.repository.inMemory.InMemoryInventoryDAO;
import br.edu.ifsp.addthenewsoul.application.repository.inMemory.InMemoryLocationDAO;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.*;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.*;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.EvaluateAssetUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.FinishInventoryUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.InventoryDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.StartInventoryUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.*;
import br.edu.ifsp.addthenewsoul.domain.usecases.report.*;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Session;

import java.time.LocalDate;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;

public class UseCaseTests {
    public static void main(String[] args) throws IOException {

	System.out.println("----- Apresentação Parcial -----");
        AssetDAO assetDAO = new InMemoryAssetDAO();
        EmployeeDAO employeeDAO = new InMemoryEmployeeDAO();
        LocationDAO locationDAO = new InMemoryLocationDAO();
        InventoryDAO inventoryDAO = new InMemoryInventoryDAO();
        EmployeeCSV employeeCSV = new EmployeeCSV();

        AddAssetUseCase addAssetUseCase = new AddAssetUseCase(assetDAO);
        UpdateAssetUseCase updateAssetUseCase = new UpdateAssetUseCase(assetDAO);
        RemoveAssetUseCase removeAssetUseCase = new RemoveAssetUseCase(assetDAO);
        FindAssetUseCase findAssetUseCase = new FindAssetUseCase(assetDAO);
        AssetCSV assetCSV = new AssetCSV();


        AddLocationUseCase addLocationUseCase = new AddLocationUseCase(locationDAO);
        UpdateLocationUseCase updateLocationUseCase = new UpdateLocationUseCase(locationDAO);
        RemoveLocationUseCase removeLocationUseCase = new RemoveLocationUseCase(locationDAO, assetDAO);
        FindLocationUseCase findLocationUseCase = new FindLocationUseCase(locationDAO);
        LocationCSV locationCSV = new LocationCSV();

        AddEmployeeUseCase addEmployeeUseCase = new AddEmployeeUseCase(employeeDAO);
        UpdateEmployeeUseCase updateEmployeeUseCase = new UpdateEmployeeUseCase(employeeDAO);
        RemoveEmployeeUseCase removeEmployeeUseCase = new RemoveEmployeeUseCase(employeeDAO, inventoryDAO);
        LoginEmployeeUseCase loginEmployeeUseCase = new LoginEmployeeUseCase(employeeDAO);
        FindEmployeeUseCase findEmployeeUseCase = new FindEmployeeUseCase(employeeDAO);
        NominateEmployeeInChargeUseCase nominateEmployeeInChargeUseCase = new NominateEmployeeInChargeUseCase(employeeDAO);
        LogoutEmployeeUseCase logoutEmployeeUseCase = new LogoutEmployeeUseCase();

        StartInventoryUseCase startInventoryUseCase = new StartInventoryUseCase(inventoryDAO);
        FinishInventoryUseCase finishInventoryUseCase = new FinishInventoryUseCase(inventoryDAO);
        EvaluateAssetUseCase evaluateAssetUseCase = new EvaluateAssetUseCase(inventoryDAO);

        ExportEmployeeCSVUseCase exportEmployeeCSVUseCase = new ExportEmployeeCSVUseCase(employeeCSV, employeeDAO);
        ImportEmployeeCSVUseCase importEmployeeCSVUseCase = new ImportEmployeeCSVUseCase(employeeCSV, employeeDAO);
        ExportAssetCSVUseCase exportAssetCSVUseCase = new ExportAssetCSVUseCase(assetCSV, assetDAO);
        ImportAssetCSVUseCase importAssetCSVUseCase = new ImportAssetCSVUseCase(assetCSV, assetDAO);
        ImportLocationCSVUseCase importLocationCSVUseCase = new ImportLocationCSVUseCase(locationCSV, locationDAO);
        ExportLocationCSVUseCase exportLocationCSVUseCase = new ExportLocationCSVUseCase(locationCSV, locationDAO);


        ReportWriter<Employee> employeeReportWriter = new EmployeeTXTReportWriter();
        ReportWriter<Inventory> inventoryReportWriter = new InventoryTXTReportWriter();
        ReportWriter<Location> locationReportWriter = new LocationTXTReportWriter();
        IssueReportUseCase issueReportUseCase = new IssueReportUseCase(employeeReportWriter, inventoryReportWriter, locationReportWriter, employeeDAO, inventoryDAO, locationDAO);


        Employee employee1 = new Employee("Walter", "R12345", "senha123", "noname@email.com", "(18) 99999-9999", Role.EXECUTOR);
        Employee employee2 = new Employee("Eisen", "R12346", "senha456", "eisen@email.com", "(16) 98888-8888", Role.CHAIRMAN_OF_THE_COMISSION);
        Employee employee3 = new Employee("Joseph", "R12347", "senha789", "joseph@email.com", "(19) 97777-7777", Role.INVENTORY_MANAGER);
        Employee employee4 = new Employee("Eric", "R22556", "senha011", "eric@email.com", "(17) 98100-7188", Role.EMPLOYEE);
        Employee employee5 = new Employee("Durandal", "R12348", "tycho123", "durandal@email.com", "(19) 97727-2777", Role.EMPLOYEE);

        addEmployeeUseCase.add(employee1);
        addEmployeeUseCase.add(employee2);
        addEmployeeUseCase.add(employee3);
        addEmployeeUseCase.add(employee4);

        FilterAssetsUseCase filterAssetsUseCase = new FilterAssetsUseCase(assetDAO, employee3);


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

        System.out.println("----- EMPLOYEE LOGOUT -----");

        logoutEmployeeUseCase.logout();

        System.out.println(Session.getInstance().getLoggedUser() == null);


        System.out.println("----- ORIGINAL LIST OF ASSETS -----");


        Asset asset1 = new Asset("Cadeira", 50.0, "Nenhum");
        Asset asset2 = new Asset("Mesa", 200.0, "Nenhum");
        Asset asset3 = new Asset("Computador Desktop", 2000.0, "Nenhum");
        Asset asset4 = new Asset("Computador portátil", 4769.0, "Nenhum");

        addAssetUseCase.add(asset1);
        addAssetUseCase.add(asset2);
        addAssetUseCase.add(asset3);
        addAssetUseCase.add(asset4);

        System.out.println(assetDAO.findAll());


        System.out.println("----- UPDATED LIST OF ASSETS -----");


        asset2.setValue(496);
        asset2.setDescription("Teclado gamer");
        updateAssetUseCase.updateAsset(asset2);
        removeAssetUseCase.removeAsset(asset2);

        System.out.println(assetDAO.findAll());


        System.out.println("----- ORIGINAL LIST OF LOCATIONS -----");


        Location location1 = new Location(10, "Seção A");
        Location location2 = new Location(20, "Seção B");
        Location location3 = new Location(30, "Seção C");
        addLocationUseCase.add(location1);
        addLocationUseCase.add(location2);
        addLocationUseCase.add(location3);

        System.out.println(locationDAO.findAll());


        System.out.println("----- ASSETS UPDATED WITH EMPLOYEES IN CHARGE AND LOCATIONS -----");

        nominateEmployeeInChargeUseCase.nominateEmployeeInCharge(employee1, asset3, location1);
        nominateEmployeeInChargeUseCase.nominateEmployeeInCharge(employee2, asset2, location2);
        nominateEmployeeInChargeUseCase.nominateEmployeeInCharge(employee3, asset1, location1);
        nominateEmployeeInChargeUseCase.nominateEmployeeInCharge(employee1, asset4, location3);
        updateAssetUseCase.updateAsset(asset2);
        updateAssetUseCase.updateAsset(asset3);
        updateAssetUseCase.updateAsset(asset1);
        updateAssetUseCase.updateAsset(asset4);

        System.out.println(findAssetUseCase.findOne(2));
        System.out.println(findAssetUseCase.findOne(3));
        System.out.println(findAssetUseCase.findOne(1));
        System.out.println(findAssetUseCase.findOne(4));


        System.out.println("----- FILTER OF ASSETS -----");

        System.out.println(filterAssetsUseCase.filterAssetsByLocal(location1));
        System.out.println(filterAssetsUseCase.filterAssetsByEmployee(employee1));
        System.out.println(filterAssetsUseCase.filterAssetsByLocationAndEmployee(location1, employee1));

        System.out.println("----- EXPORT EMPLOYEES CSV  -----");


        exportEmployeeCSVUseCase.export("employeesCsv");


        System.out.println("----- IMPORT EMPLOYEES CSV  -----");


        importEmployeeCSVUseCase.importEmployees("employeesCsv");
        System.out.println(findEmployeeUseCase.findAll());


        System.out.println("----- FIND ASSETS -----");

        System.out.println(assetDAO.findAll());

        System.out.println(assetDAO.findById(asset3.getId()));


        System.out.println("----- EXPORT ASSETS CSV  -----");


        exportAssetCSVUseCase.export("assetsCsv");


        System.out.println("----- IMPORT ASSETS CSV  -----");


        importAssetCSVUseCase.importAssets("assetsCsv");
        System.out.println(findAssetUseCase.findAll());

        System.out.println("----- EXPORT LOCATIONS CSV  -----");


        exportLocationCSVUseCase.export("locationsCsv");


        System.out.println("----- IMPORT LOCATIONS CSV  -----");


        importLocationCSVUseCase.importLocations("locationsCsv");
        System.out.println(findLocationUseCase.findAll());


        System.out.println("----- START INVENTORY -----");

        LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.of(2022, Month.NOVEMBER, 11);

        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(employee5);
        employeeList.add(employee4);

        startInventoryUseCase.initializeInventory("Teste", today, tomorrow,
                employeeList, employee3, assetDAO.findAll());

        System.out.println(inventoryDAO.findAll());

        //removeEmployeeUseCase.remove("R22556");


        System.out.println("----- FINISH INVENTORY -----");

        Inventory inventory = inventoryDAO.findInventoryById(1).get();
        inventory.leaveAssetsAsVerified();

        finishInventoryUseCase.finalizeInventory(inventory, employee3);

        System.out.println(inventoryDAO.findAll());


        System.out.println("----- LIST OF LOCATIONS -----");

        System.out.println(locationDAO.findAll());

        System.out.println(locationDAO.findByLocation(20, "Seção B"));

        System.out.println("----- UPDATE LOCATION LIST -----");


        location1.setSection("2C");
        updateLocationUseCase.update(location1);

        System.out.println(locationDAO.findAll());


        System.out.println("----- REMOVE LOCATIONS -----");

        //removeLocationUseCase.deleteLocation(location2);

        System.out.println(locationDAO.findAll());


        System.out.println("----- EMPLOYEE REPORT -----");

        //issueReportUseCase.issueEmployeeReport("R12345");

        System.out.println("----- INVENTORY REPORT -----");

        //issueReportUseCase.issueInventoryReport(1);
        issueReportUseCase.issueInventoryReport(1);

        System.out.println("----- LOCATION REPORT -----");

        //issueReportUseCase.issueLocationReport(1);

        System.out.println("----- EVALUATE ASSET -----");


        evaluateAssetUseCase.evaluateAsset(employee5, inventory.getAssets().get(0), "Risco na mesa");

        System.out.println(inventoryDAO.findAll());
        
    }
}
