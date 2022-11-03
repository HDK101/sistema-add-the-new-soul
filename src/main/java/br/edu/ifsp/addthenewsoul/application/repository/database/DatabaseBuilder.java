package br.edu.ifsp.addthenewsoul.application.repository.database;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {
    public void buildDatabaseIfMissing() {
        if (isDatabaseMissing()) {
            System.out.println("Database is missing. Building database: \n");
            buildTables();
        }
    }

    private boolean isDatabaseMissing() {
        return !Files.exists(Paths.get("persistence.db"));
    }

    private void buildTables() {
        try (Statement statement = Database.getStatement()) {
            statement.addBatch(createAssetTable());
            statement.addBatch(createInventoryAssetTable());
            statement.addBatch(createEmployeeTable());
            statement.addBatch(createInventoryTable());
            statement.addBatch(createComissionTable());
            statement.addBatch(createAssetsTable());
            statement.addBatch(createLocalTable());
            statement.executeBatch();

            System.out.println("Database successfully created.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private String createAssetTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Asset (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("description TEXT NOT NULL, \n");
        builder.append("regNumberEmployeeInCharge INTEGER, \n");
        builder.append("value DOUBLE NOT NULL, \n");
        builder.append("damage TEXT, \n");
        builder.append("location TEXT, \n");
        builder.append("FOREIGN KEY(regNumberEmployeeInCharge) REFERENCES Employee(registrationNumber)\n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createInventoryAssetTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE InventoryAsset (\n");
        builder.append("id_asset INTEGER, \n");
        builder.append("status BOOLEAN NOT NULL, \n");
        builder.append("id_executor INTEGER NOT NULL, \n");
        builder.append("PRIMARY KEY(id_asset, id_executor)");
        builder.append("FOREIGN KEY(id_asset) REFERENCES Asset(id),\n");
        builder.append("FOREIGN KEY(id_executor) REFERENCES Employee(registrationNumber)\n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createEmployeeTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Employee (\n");
        builder.append("registrationNumber INTEGER NOT NULL PRIMARY KEY, \n");
        builder.append("name TEXT NOT NULL, \n");
        builder.append("hashPassword TEXT NOT NULL, \n");
        builder.append("email TEXT NOT NULL UNIQUE, \n");
        builder.append("phone TEXT, \n");
        builder.append("role TEXT NOT NULL\n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createInventoryTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Inventory (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("name TEXT NOT NULL, \n");
        builder.append("idComissionPresident INTEGER NOT NULL, \n");
        builder.append("initialDate DATE NOT NULL, \n");
        builder.append("endDate DATE, \n");
        builder.append("FOREIGN KEY(idComissionPresident) REFERENCES Employee(registrationNumber)\n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    //This table refers to the 'list<Employee> comission' attribute found in the inventory entity
    private String createComissionTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Comission (\n");
        builder.append("id_inventory INTEGER NOT NULL, \n");
        builder.append("regNumberEmployeeInCharge INTEGER NOT NULL, \n");
        builder.append("PRIMARY KEY(id_inventory, regNumberEmployeeInCharge)");
        builder.append("FOREIGN KEY(id_inventory) REFERENCES Inventory(id)\n");
        builder.append("FOREIGN KEY(regNumberEmployeeInCharge) REFERENCES Employee(registrationNumber)\n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    //This table refers to the 'list<InventoryAssets> assets' attribute found in the inventory entity
    private String createAssetsTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Assets (\n");
        builder.append("id_inventory INTEGER NOT NULL, \n");
        builder.append("id_asset INTEGER NOT NULL, \n");
        builder.append("PRIMARY KEY(id_inventory, id_asset)");
        builder.append("FOREIGN KEY(id_asset) REFERENCES Asset(id)\n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }

    private String createLocalTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Location (\n");
        builder.append("id INTEGER INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("section TEXT NOT NULL, \n");
        builder.append("number INTEGER NOT NULL \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }
}
