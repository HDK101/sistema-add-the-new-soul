package br.edu.ifsp.addthenewsoul.application.repository.database;

import br.edu.ifsp.addthenewsoul.application.repository.database.results.ResultToAsset;
import br.edu.ifsp.addthenewsoul.application.repository.database.results.ResultToEmployee;
import br.edu.ifsp.addthenewsoul.application.repository.database.results.ResultToInventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.LocationStatus;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

public class SQLiteAssetDAO implements AssetDAO {

    private Asset resultSetToEntityWithInventoryAsset(ResultSet rs) throws SQLException {
        Asset asset = ResultToAsset.convert(rs);
        Employee employee = rs.getString("e_registration_number") != null ? ResultToEmployee.convert(rs) : null;
        InventoryAsset inventoryAsset = rs.getInt("ia_id") != 0 ? ResultToInventoryAsset.convert(rs) : null;
        asset.setEmployeeInCharge(employee);
        asset.setInventoryAsset(inventoryAsset);

        return asset;
    }

    @Override
    public Optional<Asset> findByIdWithInventoryAsset(Integer id) {
        String sql = """
                SELECT 
                    a.id AS a_id, 
                    a.description AS a_description,
                    a.employee_reg AS a_employee_reg,
                    a.damage AS a_damage,
                    a.status AS a_status,
                    a.location_id AS a_location_id,
                    a.location_status AS a_location_status,
                    
                    ia.id AS ia_id,
                    ia.damage AS ia_damage,
                    ia.description AS ia_description,
                    ia.status AS ia_status,
                    ia.location_id AS ia_location_id,
                    ia.location_status AS ia_location_status,
                    
                    e.registration_number AS e_registration_number,
                    e.name AS e_name,
                    e.phone as e_phone,
                    e.hashPassword AS e_hash_password,
                    e.email AS e_email,
                    e.role AS e_role
                FROM Asset a
                LEFT JOIN InventoryAsset ia ON ia.asset_id = a.id
                LEFT JOIN Employee e ON e.registration_number = a.employee_reg
                WHERE a.id = ?
        """;
        Asset asset = null;

        try(PreparedStatement stmt = Database.createPreparedStatement(sql)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                System.out.println(rs);
                asset = resultSetToEntityWithInventoryAsset(rs);
                //employee = resultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.of(asset);
    }

    @Override
    public Optional<Asset> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Asset> filterByLocation(Location location) {
        return null;
    }

    @Override
    public List<Asset> filterByEmployee(Employee employee) {
        return null;
    }

    @Override
    public List<Asset> filterByLocationAndEmployee(Location location, Employee employee) {
        return null;
    }

    @Override
    public synchronized Integer add(Asset asset) {
        String sql = """
                INSERT INTO Asset (
                    description,
                    value,
                    status,
                    location_status
                ) VALUES (
                    ?,
                    ?,
                    ?,
                    ?
                );
                """;
        try(PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setString(1, asset.getDescription());
            stmt.setDouble(2, asset.getValue());
            stmt.setString(3, asset.getStatus().toString());
            stmt.setString(4, LocationStatus.NORMAL.toString());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<Integer, Asset> bulkAdd(List<Asset> items) {
        return null;
    }

    @Override
    public synchronized boolean update(Asset asset) {//SQLITE
        String sql = "UPDATE Asset set description = ?, regNumberEmployeeInCharge = ?, value = ?, " +
                "damage = ?, location = ? WHERE id = ?";
        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setString(1, asset.getDescription());
            if (asset.getEmployeeInCharge() != null)
                stmt.setString(2, asset.getEmployeeInCharge().getRegistrationNumber());
            else
                stmt.setNull(2, Types.VARCHAR);

            stmt.setDouble(3, asset.getValue());
            stmt.setString(4, asset.getDamage());
            stmt.setString(5, asset.getLocation().fullLocation());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM Asset WHERE id = ?";
        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Asset> findAll() {
        String sql = "SELECT * FROM Asset";
        List<Asset> assets = new ArrayList<>();
        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            /*while (resultSet.next()) { Precisa Arrumar
                Asset asset = new Asset(
                        resultSet.getInt("id"),
                        resultSet.getString("description"),
                        resultSet.getString("regNumberEmployeeInCharge"),
                        resultSet.getDouble("value"),
                        resultSet.getString("damage"),
                        resultSet.getString("location")
                )
                assets.add(asset);
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
