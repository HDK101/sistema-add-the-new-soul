package br.edu.ifsp.addthenewsoul.domain.usecases;

import br.edu.ifsp.addthenewsoul.application.repository.database.SQLiteAssetDAO;
import br.edu.ifsp.addthenewsoul.application.repository.database.SQLiteEmployeeDAO;
import br.edu.ifsp.addthenewsoul.application.repository.database.SQLiteInventoryDAO;
import br.edu.ifsp.addthenewsoul.application.repository.database.SQLiteLocationDAO;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.*;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.*;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.*;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.*;
import br.edu.ifsp.addthenewsoul.domain.usecases.report.EmployeePDFReportWriter;
import br.edu.ifsp.addthenewsoul.domain.usecases.report.InventoryPDFReportWriter;
import br.edu.ifsp.addthenewsoul.domain.usecases.report.IssueReportUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.report.LocationPDFReportWriter;

import java.util.List;

public class UseCases {
    private static UseCases instance;

    public AssetDAO assetDAO;
    public EmployeeDAO employeeDAO;
    public LocationDAO locationDAO;
    public InventoryDAO inventoryDAO;

    public AddLocationUseCase addLocationUseCase;
    public UpdateLocationUseCase updateLocationUseCase;
    public FindLocationUseCase findLocationUseCase;
    public RemoveLocationUseCase removeLocationUseCase;

    public AddAssetUseCase addAssetUseCase;
    public UpdateAssetUseCase updateAssetUseCase;
    public FindAssetUseCase findAssetUseCase;
    public RemoveAssetUseCase removeAssetUseCase;
    public FilterAssetsUseCase filterAssetsUseCase;

    public LoginEmployeeUseCase loginEmployeeUseCase;
    public AddEmployeeUseCase addEmployeeUseCase;
    public UpdateEmployeeUseCase updateEmployeeUseCase;
    public RemoveEmployeeUseCase removeEmployeeUseCase;
    public FindEmployeeUseCase findEmployeeUseCase;
    public ExportEmployeeCSVUseCase exportEmployeeCSVUseCase;
    public ImportEmployeeCSVUseCase importEmployeeCSVUseCase;

    public EvaluateAssetUseCase evaluateAssetUseCase;
    public StartInventoryUseCase startInventoryUseCase;
    public FinishInventoryUseCase finishInventoryUseCase;
    public FindInventoryUseCase findInventoryUseCase;

    public ImportAssetCSVUseCase importAssetCSVUseCase;
    public ExportAssetCSVUseCase exportAssetCSVUseCase;

    public ExportLocationCSVUseCase exportLocationCSVUseCase;
    public ImportLocationCSVUseCase importLocationCSVUseCase;

    public static IssueReportUseCase issueReportUseCase;

    public UseCases() {
        EmployeeCSV employeeCSV = new EmployeeCSV();
        AssetCSV assetCSV = new AssetCSV();
        LocationCSV locationCSV = new LocationCSV();

        assetDAO = new SQLiteAssetDAO();
        employeeDAO = new SQLiteEmployeeDAO();
        locationDAO = new SQLiteLocationDAO();
        inventoryDAO = new SQLiteInventoryDAO();

        loginEmployeeUseCase = new LoginEmployeeUseCase(employeeDAO);

        addLocationUseCase = new AddLocationUseCase(locationDAO);
        updateLocationUseCase = new UpdateLocationUseCase(locationDAO);
        findLocationUseCase = new FindLocationUseCase(locationDAO);
        removeLocationUseCase = new RemoveLocationUseCase(locationDAO);

        addEmployeeUseCase = new AddEmployeeUseCase(employeeDAO);
        findEmployeeUseCase = new FindEmployeeUseCase(employeeDAO);
        updateEmployeeUseCase = new UpdateEmployeeUseCase(employeeDAO);
        removeEmployeeUseCase = new RemoveEmployeeUseCase(employeeDAO, inventoryDAO);

        List<Employee> employee = findEmployeeUseCase.findAll();
        addAssetUseCase = new AddAssetUseCase(assetDAO);
        findAssetUseCase = new FindAssetUseCase(assetDAO);
        updateAssetUseCase = new UpdateAssetUseCase(assetDAO);
        removeAssetUseCase = new RemoveAssetUseCase(assetDAO);
        filterAssetsUseCase = new FilterAssetsUseCase(assetDAO, employee.get(0));

        startInventoryUseCase = new StartInventoryUseCase(inventoryDAO);
        findInventoryUseCase = new FindInventoryUseCase(inventoryDAO);
        finishInventoryUseCase = new FinishInventoryUseCase(inventoryDAO);
        evaluateAssetUseCase = new EvaluateAssetUseCase(inventoryDAO);

        exportEmployeeCSVUseCase = new ExportEmployeeCSVUseCase(employeeCSV, employeeDAO);
        importEmployeeCSVUseCase = new ImportEmployeeCSVUseCase(employeeCSV, employeeDAO);

        exportAssetCSVUseCase = new ExportAssetCSVUseCase(assetCSV, assetDAO);
        importAssetCSVUseCase = new ImportAssetCSVUseCase(assetCSV, assetDAO);

        exportLocationCSVUseCase = new ExportLocationCSVUseCase(locationCSV, locationDAO);
        importLocationCSVUseCase = new ImportLocationCSVUseCase(locationCSV, locationDAO);

        findInventoryUseCase = new FindInventoryUseCase(inventoryDAO);

        EmployeePDFReportWriter employeePDFReportWriter = new EmployeePDFReportWriter();
        InventoryPDFReportWriter inventoryPDFReportWriter = new InventoryPDFReportWriter();
        LocationPDFReportWriter locationPDFReportWriter = new LocationPDFReportWriter();

        issueReportUseCase = new IssueReportUseCase(employeePDFReportWriter, inventoryPDFReportWriter, locationPDFReportWriter, employeeDAO, inventoryDAO, locationDAO);
    }

    public static UseCases getInstance() {
        if (instance == null) instance = new UseCases();
        return instance;
    }

    public static void init() {
        if (instance == null) instance = new UseCases();
    }
}
