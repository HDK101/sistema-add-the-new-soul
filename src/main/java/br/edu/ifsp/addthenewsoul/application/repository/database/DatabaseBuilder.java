package br.edu.ifsp.addthenewsoul.application.repository.database;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {
    public static void main(String[] args) {
        Database.getConnection();
        buildDatabaseIfMissing();
    }

    public static void buildDatabaseIfMissing() {
        //if (isDatabaseMissing()) {
            System.out.println("Database is missing. Building database: \n");
            buildTables();
        //}
    }

    private static boolean isDatabaseMissing() {
        return !Files.exists(Paths.get("persistence.db"));
    }

    private static void buildTables() {
        try (Statement statement = Database.getStatement()) {
            statement.addBatch(createAssetTable());
            statement.addBatch(createEmployeeTable());
            statement.addBatch(createEmployeeRoleTable());
            statement.addBatch(createInventoryTable());
            statement.addBatch(createInventoryAssetTable());
            statement.addBatch(createComissionTable());
            statement.addBatch(createLocationTable());
            statement.executeBatch();

            System.out.println("Database successfully created.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private static String createAssetTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Asset (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("description TEXT NOT NULL, \n");
        builder.append("employee_reg TEXT, \n");
        builder.append("value DOUBLE NOT NULL, \n");
        builder.append("damage TEXT, \n");
        builder.append("status TEXT NOT NULL DEFAULT 'NOT_VERIFIED', \n");
        builder.append("location_id INTEGER, \n");
        builder.append("location_status TEXT NOT NULL DEFAULT 'NONE', \n");
        builder.append("FOREIGN KEY(employee_reg) REFERENCES Employee(registration_number), \n");
        builder.append("FOREIGN KEY(location_id) REFERENCES Location(id)\n");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }

    private static String createEmployeeTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Employee (\n");
        builder.append("registration_number TEXT NOT NULL PRIMARY KEY, \n");
        builder.append("name TEXT NOT NULL, \n");
        builder.append("hash_password TEXT NOT NULL, \n");
        builder.append("email TEXT NOT NULL UNIQUE, \n");
        builder.append("phone TEXT \n");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }

    private static String createEmployeeRoleTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE EmployeeRole (\n");
        builder.append("employee_reg TEXT NOT NULL, \n");
        builder.append("role TEXT NOT NULL,\n");
        builder.append("PRIMARY KEY(employee_reg, role),\n");
        builder.append("FOREIGN KEY(employee_reg) REFERENCES Employee(registration_number)\n");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }

    private static String createInventoryAssetTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE InventoryAsset (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("asset_id INTEGER NOT NULL, \n");
        builder.append("inventory_id TEXT NOT NULL, \n");
        builder.append("location_id INTEGER, \n");
        builder.append("inventory_manager_reg INTEGER, \n");
        builder.append("employee_reg TEXT, \n");

        builder.append("description TEXT NOT NULL, \n");
        builder.append("value DOUBLE NOT NULL, \n");
        builder.append("damage TEXT, \n");
        builder.append("status TEXT NOT NULL DEFAULT 'NOT_VERIFIED', \n");
        builder.append("location_status TEXT, \n");

        builder.append("FOREIGN KEY(asset_id) REFERENCES Asset(id),\n");
        builder.append("FOREIGN KEY(inventory_id) REFERENCES Inventory(id),\n");
        builder.append("FOREIGN KEY(employee_reg) REFERENCES Employee(registration_number),\n");
        builder.append("FOREIGN KEY(inventory_manager_reg) REFERENCES Employee(registration_number)\n");
        builder.append("FOREIGN KEY(location_id) REFERENCES Location(id)\n");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }

    private static String createInventoryTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Inventory (\n");
        builder.append("id TEXT PRIMARY KEY, \n");
        builder.append("name TEXT NOT NULL, \n");
        builder.append("president_reg TEXT NOT NULL, \n");
        builder.append("initial_date DATE NOT NULL, \n");
        builder.append("status TEXT NOT NULL DEFAULT 'OPEN', \n");
        builder.append("end_date DATE, \n");
        builder.append("inventory_asset_id INTEGER, \n");
        builder.append("FOREIGN KEY(president_reg) REFERENCES Employee(registration_number)\n");
        builder.append("FOREIGN KEY(inventory_asset_id) REFERENCES InventoryAsset(id)\n");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }

    //This table refers to the 'list<Employee> comission' attribute found in the inventory entity
    private static String createComissionTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Comission (\n");
        builder.append("id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("id_inventory INTEGER NOT NULL, \n");
        builder.append("employee_reg INTEGER NOT NULL, \n");
        builder.append("FOREIGN KEY(id_inventory) REFERENCES Inventory(id)\n");
        builder.append("FOREIGN KEY(employee_reg) REFERENCES Employee(registrationNumber)\n");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }

    private static String createLocationTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Location (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("section TEXT NOT NULL, \n");
        builder.append("number INTEGER NOT NULL \n");
        builder.append("); \n");

        System.out.println(builder.toString());
        return builder.toString();
    }
}
