package br.edu.ifsp.addthenewsoul.application.repository.database;

import br.edu.ifsp.addthenewsoul.application.repository.database.results.ResultToAsset;
import br.edu.ifsp.addthenewsoul.application.repository.database.results.ResultToEmployee;
import br.edu.ifsp.addthenewsoul.application.repository.database.results.ResultToInventory;
import br.edu.ifsp.addthenewsoul.application.repository.database.results.ResultToInventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.InventoryDAO;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class SQLiteInventoryDAO implements InventoryDAO {


    private Inventory resultSetToInventoryWithEmployee(ResultSet rs) throws SQLException {
        List<InventoryAsset> inventoryAssets = new ArrayList<>();
        List<Employee> employees = new ArrayList<>();
        Inventory inventory = ResultToInventory.convert(rs);
        Employee employee = rs.getString("e_registration_number") != null ? ResultToEmployee.convert(rs) : null;
        InventoryAsset inventoryAsset = rs.getInt("ia_id") != 0 ? ResultToInventoryAsset.convert(rs) : null;
        inventoryAssets.add(inventoryAsset);
        employees.add(employee);

        assert inventoryAsset != null;
        inventoryAsset.setInventory(inventory);
        inventoryAsset.setInventoryManager(employee);
        inventory.setComissionPresident(employee);
        inventory.setComission(employees);
        inventory.setAssets(inventoryAssets);

        return inventory;
    }
    @Override
    public Optional<Inventory> findInventoryById(String id) {
        String sql = """
                SELECT
                    i.id AS i_id,
                    i.name AS i_name,
                    i.president_reg AS i_president_reg,
                    i.initial_date AS i_initial_date,
                    i.end_date AS i_end_date,
                    i.status AS i_inventory_status
                    
                    ia.id AS ia_id,
                    ia.inventory_id AS ia_inventory_id,
                    ia.damage AS ia_damage,
                    ia.description AS ia_description,
                    ia.status AS ia_status,
                    ia.value AS ia_value,
                    ia.location_id AS ia_location_id,
                    ia.location_status AS ia_location_status
                FROM Inventory i
                LEFT JOIN InventoryAsset ia ON ia.inventory_id = i.id
                WHERE i.id = ?
                """;

        try (PreparedStatement statement = Database.createPreparedStatement(sql)) {
            statement.setString(1, id);
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            Inventory inventory = null;
            while (resultSet.next()) {
                if (inventory == null) inventory = ResultToInventory.convert(resultSet);
                InventoryAsset inventoryAsset = ResultToInventoryAsset.convert(resultSet);
                inventory.addAsset(inventoryAsset);
            }
            return Optional.ofNullable(inventory);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void filterByPeriod(List<Inventory> all, LocalDate initialDate, LocalDate endDate) {

    }

    @Override
    public boolean getStatusFromInventories() {
        return false;
    }

    @Override
    public Map<String, Inventory> bulkAdd(List<Inventory> items) {
        return null;
    }

    private boolean addAsset(String inventoryId, InventoryAsset inventoryAsset) {
        String sql = """
                INSERT INTO InventoryAsset (
                    asset_id,
                    inventory_id,
                    location_id,
                    inventory_manager_reg,
                    employee_reg,
                    description,
                    value,
                    damage,
                    status,
                    location_status
                ) VALUES (
                    ?,
                    ?,
                    ?,
                    ?,
                    ?,
                    ?,
                    ?,
                    ?,
                    ?,
                    ?
                );
                """;
        try(PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setInt(1, inventoryAsset.getAsset().getId());
            stmt.setString(2, inventoryId);

            if (inventoryAsset.getLocation() == null) {
                stmt.setNull(3, Types.INTEGER);
            } else {
                stmt.setInt(3, inventoryAsset.getLocation().getId());
            }

            if (inventoryAsset.getInventoryManager() == null) {
                stmt.setNull(4, Types.VARCHAR);
            } else {
                stmt.setString(4, inventoryAsset.getInventoryManager().getRegistrationNumber());
            }

            if (inventoryAsset.getEmployeeInCharge() == null) {
                stmt.setNull(5, Types.VARCHAR);
            } else {
                stmt.setString(5, inventoryAsset.getEmployeeInCharge().getRegistrationNumber());
            }

            stmt.setString(6, inventoryAsset.getDescription());
            stmt.setDouble(7, inventoryAsset.getValue());
            stmt.setString(8, inventoryAsset.getDamage());
            stmt.setString(9, inventoryAsset.getStatus().toString());
            stmt.setString(10, inventoryAsset.getLocationStatus().toString());
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean addAssets(String inventoryId, List<InventoryAsset> inventoryAssets) {
        for (InventoryAsset inventoryAsset : inventoryAssets) {
            if (this.addAsset(inventoryId, inventoryAsset)) return false;
        }
        return true;
    }

    private boolean addInventory(String inventoryId, Inventory inventory) {
        String sql = """
                INSERT INTO Inventory (
                    id,
                    name,
                    president_reg,
                    initial_date,
                    end_date,
                    status
                ) VALUES (
                    ?,
                    ?,
                    ?,
                    ?,
                    ?,
                    ?
                );
                """;
        try(PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setString(1, inventoryId);
            stmt.setString(2, inventory.getName());
            stmt.setString(3, inventory.getComissionPresident().getRegistrationNumber());
            stmt.setDate(4, Date.valueOf(inventory.getInitialDate()));
            stmt.setDate(5, Date.valueOf(inventory.getEndDate()));
            stmt.setString(6, inventory.getInventoryStatus().toString());

            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String add(Inventory inventory) {
        Connection connection = null;

        String uuid = UUID.randomUUID().toString();

        String sqlInventory = """
                INSERT INTO Inventory (
                    id,
                    name,
                    president_reg,
                    initial_date,
                    end_date,
                    status
                ) VALUES (
                    ?,
                    ?,
                    ?,
                    ?,
                    ?,
                    ?
                );
                """;

        String sqlInventoryAsset = """
                INSERT INTO InventoryAsset (
                    asset_id,
                    inventory_id,
                    location_id,
                    inventory_manager_reg,
                    employee_reg,
                    description,
                    value,
                    damage,
                    status,
                    location_status
                ) VALUES (
                    ?,
                    ?,
                    ?,
                    ?,
                    ?,
                    ?,
                    ?,
                    ?,
                    ?,
                    ?
                );
                """;

        String sqlComissionInventory = """
                INSERT INTO Comission (
                    id_inventory,
                    employee_reg
                ) VALUES (
                    ?,
                    ?
                );
                """;

        List<InventoryAsset> inventoryAsset = inventory.getAssets();
        try {
            connection = Database.getConnection();
            try (PreparedStatement stmtInventory = Database.createPreparedStatement(sqlInventory);
                 PreparedStatement stmtInventoryAsset = Database.createPreparedStatement(sqlInventoryAsset)) {
                connection.setAutoCommit(false);

                stmtInventory.setString(1, uuid);
                stmtInventory.setString(2, inventory.getName());
                stmtInventory.setString(3, inventory.getComissionPresident().getRegistrationNumber());
                stmtInventory.setDate(4, Date.valueOf(inventory.getInitialDate()));
                stmtInventory.setDate(5, Date.valueOf(inventory.getEndDate()));
                stmtInventory.setString(6, inventory.getInventoryStatus().toString());
                stmtInventory.executeUpdate();

                for (InventoryAsset asset : inventoryAsset) {
                    stmtInventoryAsset.setInt(1, asset.getAsset().getId());
                    stmtInventoryAsset.setString(2, uuid);

                    if (asset.getLocation() == null) {
                        stmtInventoryAsset.setNull(3, Types.INTEGER);
                    } else {
                        stmtInventoryAsset.setInt(3, asset.getLocation().getId());
                    }

                    if (asset.getInventoryManager() == null) {
                        stmtInventoryAsset.setNull(4, Types.VARCHAR);
                    } else {
                        stmtInventoryAsset.setString(4, asset.getInventoryManager().getRegistrationNumber());
                    }

                    if (asset.getEmployeeInCharge() == null) {
                        stmtInventoryAsset.setNull(5, Types.VARCHAR);
                    } else {
                        stmtInventoryAsset.setString(5, asset.getEmployeeInCharge().getRegistrationNumber());
                    }

                    stmtInventoryAsset.setString(6, asset.getDescription());
                    stmtInventoryAsset.setDouble(7, asset.getValue());
                    stmtInventoryAsset.setString(8, asset.getDamage());
                    stmtInventoryAsset.setString(9, asset.getStatus().toString());
                    stmtInventoryAsset.setString(10, asset.getLocationStatus().toString());
                }
                stmtInventoryAsset.executeUpdate();

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("Erro");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return uuid;
    }

    @Override
    public boolean update(Inventory type) {
        return false;
    }

    @Override
    public boolean delete(String key) {
        return false;
    }

    @Override
    public List<Inventory> findAll() {
        String sql = """
                SELECT
                    i.id AS i_id,
                    i.name AS i_name,
                    i.president_reg AS i_president_reg,
                    i.initial_date AS i_initial_date,
                    i.end_date AS i_end_date,
                    i.status AS i_inventory_status,
                    
                    e.registration_number AS e_registration_number,
                    e.name AS e_name,
                    e.phone as e_phone,
                    e.hash_password AS e_hash_password,
                    e.email AS e_email,
                    er.role AS er_role,
                    
                    ia.id AS ia_id,
                    ia.asset_id AS ia_asset_id,
                    ia.inventory_id AS ia_inventory_id,
                    ia.damage AS ia_damage,
                    ia.description AS ia_description,
                    ia.status AS ia_status,
                    ia.value AS ia_value,
                    ia.location_id AS ia_location_id,
                    ia.location_status AS ia_location_status,
                    
                    a.id AS a_id,
                    a.description AS a_description,
                    a.employee_reg AS a_employee_reg,
                    a.value AS a_value,
                    a.damage AS a_damage,
                    a.status AS a_status,
                    a.location_id AS a_location_id,
                    a.location_status AS a_location_status
                FROM Inventory i
                INNER JOIN Employee e ON e.registration_number = i.president_reg
                LEFT JOIN EmployeeRole er ON e.registration_number = er.employee_reg
                LEFT JOIN InventoryAsset ia ON ia.inventory_id = i.id
                LEFT JOIN Asset a ON a.id = ia.asset_id
                """;

        Inventory inventory = null;
        List<Inventory> inventories = new ArrayList<>();

        try (PreparedStatement statement = Database.createPreparedStatement(sql)) {
            statement.execute();

            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                inventory = resultSetToInventoryWithEmployee(resultSet);
                inventories.add(inventory);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inventories;
    }
}
