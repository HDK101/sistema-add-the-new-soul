package br.edu.ifsp.addthenewsoul.application.repository.database;

import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.InventoryDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class SQLiteInventoryDAO implements InventoryDAO {
    @Override
    public Optional<Inventory> findInventoryById(String id) {
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
                INSERT INTO InventoryAssets (
                    asset_id,
                    inventory_id,
                    location_id,
                    inventory_manager_reg,
                    employee_reg
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
                    president_id,
                    initial_date,
                    end_date
                ) VALUES (
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
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String add(Inventory inventory) {
        Connection connection = Database.getConnection();

        String uuid = UUID.randomUUID().toString();

        try {
            connection.setAutoCommit(false);

            boolean inventoryAddSuccess = this.addInventory(uuid, inventory);
            boolean assetsAddSuccess = this.addAssets(uuid, inventory.getAssets());

            if (!inventoryAddSuccess || !assetsAddSuccess) {
                connection.rollback();
            }

            connection.commit();
        } catch (SQLException e) {
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
        return null;
    }
}
