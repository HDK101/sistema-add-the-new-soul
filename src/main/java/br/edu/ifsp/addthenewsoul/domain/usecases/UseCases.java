package br.edu.ifsp.addthenewsoul.domain.usecases;

import br.edu.ifsp.addthenewsoul.application.repository.database.SQLiteAssetDAO;
import br.edu.ifsp.addthenewsoul.application.repository.database.SQLiteEmployeeDAO;
import br.edu.ifsp.addthenewsoul.application.repository.database.SQLiteInventoryDAO;
import br.edu.ifsp.addthenewsoul.application.repository.database.SQLiteLocationDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.*;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.*;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.*;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.*;
import br.edu.ifsp.addthenewsoul.domain.usecases.report.EmployeePDFReportWriter;
import br.edu.ifsp.addthenewsoul.domain.usecases.report.InventoryPDFReportWriter;
import br.edu.ifsp.addthenewsoul.domain.usecases.report.IssueReportUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.report.LocationPDFReportWriter;

public class UseCases {
    private static UseCases instance;

    public AssetDAO assetDAO;
    public EmployeeDAO employeeDAO;
    public LocationDAO locationDAO;
    public InventoryDAO inventoryDAO;

    public static AddLocationUseCase addLocationUseCase;
    public static UpdateLocationUseCase updateLocationUseCase;
    public static FindLocationUseCase findLocationUseCase;
    public static RemoveLocationUseCase removeLocationUseCase;

    public static AddAssetUseCase addAssetUseCase;

    public static UpdateAssetUseCase updateAssetUseCase;

    public static FindAssetUseCase findAssetUseCase;

    public static RemoveAssetUseCase removeAssetUseCase;

    public static FilterAssetsUseCase filterAssetsUseCase;

    public static LoginEmployeeUseCase loginEmployeeUseCase;
    public static AddEmployeeUseCase addEmployeeUseCase;
    public static UpdateEmployeeUseCase updateEmployeeUseCase;
    public static RemoveEmployeeUseCase removeEmployeeUseCase;
    public static FindEmployeeUseCase findEmployeeUseCase;
    public static ExportEmployeeCSVUseCase exportEmployeeCSVUseCase;
    public static ImportEmployeeCSVUseCase importEmployeeCSVUseCase;

    public static EvaluateAssetUseCase evaluateAssetUseCase;
    public static StartInventoryUseCase startInventoryUseCase;
    public static FinishInventoryUseCase finishInventoryUseCase;
    public static FindInventoryUseCase findInventoryUseCase;

    public static ImportAssetCSVUseCase importAssetCSVUseCase;
    public static ExportAssetCSVUseCase exportAssetCSVUseCase;

    public static ExportLocationCSVUseCase exportLocationCSVUseCase;
    public static ImportLocationCSVUseCase importLocationCSVUseCase;

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

        addAssetUseCase = new AddAssetUseCase(assetDAO);
        findAssetUseCase = new FindAssetUseCase(assetDAO);
        updateAssetUseCase = new UpdateAssetUseCase(assetDAO);
        removeAssetUseCase = new RemoveAssetUseCase(assetDAO);

        exportEmployeeCSVUseCase = new ExportEmployeeCSVUseCase(employeeCSV, employeeDAO);
        importEmployeeCSVUseCase = new ImportEmployeeCSVUseCase(employeeCSV, employeeDAO);

        exportAssetCSVUseCase = new ExportAssetCSVUseCase(assetCSV, assetDAO);
        importAssetCSVUseCase = new ImportAssetCSVUseCase(assetCSV, assetDAO);

        exportLocationCSVUseCase = new ExportLocationCSVUseCase(locationCSV, locationDAO);
        importLocationCSVUseCase = new ImportLocationCSVUseCase(locationCSV, locationDAO);

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
