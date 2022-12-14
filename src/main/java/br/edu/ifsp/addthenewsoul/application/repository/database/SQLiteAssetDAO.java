package br.edu.ifsp.addthenewsoul.application.repository.database;

import br.edu.ifsp.addthenewsoul.application.repository.database.results.ResultToAsset;
import br.edu.ifsp.addthenewsoul.application.repository.database.results.ResultToEmployee;
import br.edu.ifsp.addthenewsoul.application.repository.database.results.ResultToInventoryAsset;
import br.edu.ifsp.addthenewsoul.application.repository.database.results.ResultToLocation;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.LocationStatus;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
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

    private Asset resultSetToEntityWithLocationAndEmployee(ResultSet rs) throws SQLException {
        Asset asset = ResultToAsset.convert(rs);
        Employee employee = rs.getString("e_registration_number") != null ? ResultToEmployee.convert(rs) : null;
        Location location = rs.getInt("l_id") != 0 ? ResultToLocation.convert(rs) : null;
        asset.setEmployeeInCharge(employee);
        asset.setLocation(location);
        System.out.println(asset.getEmployeeInCharge());
        return asset;
    }

    private Asset resultSetToEntityWithLocation(ResultSet rs) throws SQLException {
        Asset asset = ResultToAsset.convert(rs);
        Location location = rs.getInt("l_id") != 0 ? ResultToLocation.convert(rs) : null;
        asset.setLocation(location);
        return asset;
    }

    private Asset resultSetToEntityWithEmployee(ResultSet rs) throws SQLException {
        Asset asset = ResultToAsset.convert(rs);
        Employee employee = rs.getString("e_registration_number") != null ? ResultToEmployee.convert(rs) : null;
        asset.setEmployeeInCharge(employee);
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
                            a.value AS a_value,
                            a.location_id AS a_location_id,
                            a.location_status AS a_location_status,
                            
                            ia.id AS ia_id,
                            ia.damage AS ia_damage,
                            ia.description AS ia_description,
                            ia.status AS ia_status,
                            ia.value AS ia_value,
                            ia.location_id AS ia_location_id,
                            ia.location_status AS ia_location_status,
                            
                            e.registration_number AS e_registration_number,
                            e.name AS e_name,
                            e.phone as e_phone,
                            e.hash_password AS e_hash_password,
                            e.email AS e_email
                        FROM Asset a
                        LEFT JOIN InventoryAsset ia ON ia.asset_id = a.id
                        LEFT JOIN Employee e ON e.registration_number = a.employee_reg
                        WHERE a.id = ?
                """;
        Asset asset = null;

        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                asset = resultSetToEntityWithInventoryAsset(rs);
                //employee = resultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(asset);
    }

    @Override
    public Optional<Asset> findById(Integer id) {
        String sql = """
                    SELECT
                        a.id AS a_id,
                        a.description AS a_description,
                        a.employee_reg AS a_employee_reg,
                        a.damage AS a_damage,
                        a.status AS a_status,
                        a.value AS a_value,
                        a.location_id AS a_location_id,
                        a.location_status AS a_location_status
                    FROM Asset a
                    WHERE a.id = ?
                """;
        Asset asset = null;
        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                asset = ResultToAsset.convert(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(asset);
    }

    @Override
    public List<Asset> filterByLocation(Location location) {
        String sql = """
                    SELECT
                        a.id AS a_id,
                        a.description AS a_description,
                        a.employee_reg AS a_employee_reg,
                        a.value AS a_value,
                        a.damage AS a_damage,
                        a.status AS a_status,
                        a.location_id AS a_location_id,
                        a.location_status AS a_location_status,
                        
                        l.id AS l_id,
                        l.section AS l_section,
                        l.number AS l_number
                    FROM Asset a
                    LEFT JOIN Location l ON l.id = a.location_id
                    WHERE a.location_id = ?
                """;
        Asset asset = null;
        List<Asset> assets = new ArrayList<>();
        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setInt(1, location.getId());

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                asset = resultSetToEntityWithLocation(resultSet);
                assets.add(asset);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assets;
    }

    @Override
    public List<Asset> filterByEmployee(Employee employee) {
        String sql = """
                    SELECT
                        a.id AS a_id,
                        a.description AS a_description,
                        a.employee_reg AS a_employee_reg,
                        a.value AS a_value,
                        a.damage AS a_damage,
                        a.status AS a_status,
                        a.location_id AS a_location_id,
                        a.location_status AS a_location_status,
                        
                        e.registration_number AS e_registration_number,
                        e.name AS e_name,
                        e.phone as e_phone,
                        e.hash_password AS e_hash_password,
                        e.email AS e_email,
                        
                        l.id AS l_id,
                        l.section AS l_section,
                        l.number AS l_number
                    FROM Asset a
                    LEFT JOIN Employee e ON e.registration_number = a.employee_reg
                    LEFT JOIN Location l ON l.id = a.location_id
                    WHERE a.employee_reg = ?
                """;
        Asset asset = null;
        List<Asset> assets = new ArrayList<>();
        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setString(1, employee.getRegistrationNumber());
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                asset = resultSetToEntityWithEmployee(resultSet);
                assets.add(asset);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assets;
    }

    @Override
    public boolean evaluateAsset(Asset asset) {
        String sql = """
                    UPDATE Asset set
                        damage = ?,
                        location_status = ?
                    WHERE id = ?
                """;

        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setString(1, asset.getDamage());
            stmt.setString(2, asset.getLocationStatus().toString());
            stmt.setInt(3, asset.getId());

            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Asset> filterByLocationAndEmployee(Location location, Employee employee) {
        String sql = """
                    SELECT
                        a.id AS a_id,
                        a.description AS a_description,
                        a.employee_reg AS a_employee_reg,
                        a.value AS a_value,
                        a.damage AS a_damage,
                        a.status AS a_status,
                        a.location_id AS a_location_id,
                        a.location_status AS a_location_status,
                        
                        l.id AS l_id,
                        l.section AS l_section,
                        l.number AS l_number,
                        
                        e.registration_number AS e_registration_number,
                        e.name AS e_name,
                        e.phone as e_phone,
                        e.hash_password AS e_hash_password,
                        e.email AS e_email
                    FROM Asset a
                    LEFT JOIN Location l ON l.id = a.location_id
                    LEFT JOIN Employee e ON e.registration_number = a.employee_reg
                    WHERE a.location_id = ? AND a.employee_reg = ?
                """;
        Asset asset = null;

        List<Asset> assets = new ArrayList<>();
        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setInt(1, location.getId());
            stmt.setString(2, employee.getRegistrationNumber());
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                asset = resultSetToEntityWithLocationAndEmployee(resultSet);
                assets.add(asset);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assets;
    }

    @Override
    public synchronized Integer add(Asset asset) {
        String sql = """
                INSERT INTO Asset (
                    description,
                    value,
                    status,
                    employee_reg,
                    location_id,
                    location_status,
                    damage
                ) VALUES (
                    ?,
                    ?,
                    ?,
                    ?,
                    ?,
                    ?,
                    ?
                );
                """;
        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setString(1, asset.getDescription());
            stmt.setDouble(2, asset.getValue());
            stmt.setString(3, asset.getStatus().toString());
            stmt.setString(4, asset.getEmployeeInCharge().getRegistrationNumber());
            if (asset.getLocation() != null) {
                stmt.setInt(5, asset.getLocation().getId());
            }
            else {
                stmt.setNull(5, Types.INTEGER);
            }
            stmt.setString(6, LocationStatus.NONE.toString());
            stmt.setString(7, null);
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
        HashMap<Integer, Asset> assetHashMap = new HashMap<>();

        // Tentei executar em batch, nao funciona, vai assim msm

        items.forEach(item -> {
            Integer id = this.add(item);
            if (id != null) assetHashMap.put(item.getId(), item);
        });

        return assetHashMap;
    }

    @Override
    public synchronized boolean update(Asset asset) {//SQLITE
        String sql = """
                    UPDATE Asset set
                        description = ?,
                        employee_reg = ?,
                        value = ?,
                        damage = ?,
                        location_id = ?,
                        status = ?
                    WHERE id = ?
                """;

        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setString(1, asset.getDescription());
            if (asset.getEmployeeInCharge() != null)
                stmt.setString(2, asset.getEmployeeInCharge().getRegistrationNumber());
            else
                stmt.setNull(2, Types.VARCHAR);

            stmt.setDouble(3, asset.getValue());
            stmt.setString(4, asset.getDamage());

            if (asset.getLocation() != null) {
                stmt.setInt(5, asset.getLocation().getId());
            }
            else {
                stmt.setNull(5, Types.INTEGER);
            }

            stmt.setString(6, asset.getStatus().toString());
            stmt.setInt(7, asset.getId());
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
        String sql = """
                    SELECT
                        a.id AS a_id,
                        a.description AS a_description,
                        a.employee_reg AS a_employee_reg,
                        a.value AS a_value,
                        a.damage AS a_damage,
                        a.status AS a_status,
                        a.location_id AS a_location_id,
                        a.location_status AS a_location_status,
                        
                        l.id AS l_id,
                        l.section AS l_section,
                        l.number AS l_number,
                        
                        e.registration_number AS e_registration_number,
                        e.name AS e_name,
                        e.phone as e_phone,
                        e.hash_password AS e_hash_password,
                        e.email AS e_email,
                        
                        er.employee_reg AS er_employee_reg,
                        er.role AS er_role
                    FROM Asset a
                    LEFT JOIN Location l ON l.id = a.location_id
                    LEFT JOIN Employee e ON e.registration_number = a.employee_reg
                    LEFT JOIN EmployeeRole er ON er.employee_reg = e.registration_number
                """;
        Asset asset = null;

        Set<Asset> assets = new HashSet<>();
        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                asset = resultSetToEntityWithLocationAndEmployee(resultSet);
                assets.add(asset);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assets.stream().toList();
    }


}



