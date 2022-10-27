package br.edu.ifsp.addthenewsoul.application.database;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLiteAssetDAO implements AssetDAO {

    @Override
    public Optional<Asset> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public synchronized Integer add(Asset asset) {
        String sql = "INSERT INTO Asset (description, regNumberEmployeeInCharge, value, " +
                "damage, location) VALUES (?,?,?,?,?)";
        try(PreparedStatement stmt = Database.createPrepareStatement(sql)) {
            stmt.setString(1, asset.getDescription());
            stmt.setString(2, asset.getEmployeeInCharge().getRegistrationNumber());
            stmt.setDouble(3, asset.getValue());
            stmt.setString(4, asset.getDamage());
            stmt.setString(5, asset.getLocation().fullLocation());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public synchronized boolean update(Asset asset) {
        String sql = "UPDATE Asset set description = ?, regNumberEmployeeInCharge = ?, value = ?, " +
                "damage = ?, location = ? WHERE id = ?";
        try (PreparedStatement stmt = Database.createPrepareStatement(sql)) {
            stmt.setString(1, asset.getDescription());
            stmt.setString(2, asset.getEmployeeInCharge().getRegistrationNumber());
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
        try (PreparedStatement stmt = Database.createPrepareStatement(sql)) {
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
        try (PreparedStatement stmt = Database.createPrepareStatement(sql)) {
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
