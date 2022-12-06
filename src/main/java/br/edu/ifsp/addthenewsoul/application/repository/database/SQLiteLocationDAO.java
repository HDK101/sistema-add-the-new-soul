package br.edu.ifsp.addthenewsoul.application.repository.database;

import br.edu.ifsp.addthenewsoul.application.repository.database.results.ResultToAsset;
import br.edu.ifsp.addthenewsoul.application.repository.database.results.ResultToLocation;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.LocationDAO;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SQLiteLocationDAO implements LocationDAO {

    @Override
    public Integer add(Location location) {
        String sql = """
                INSERT INTO Location (
                    number,
                    section
                ) VALUES (
                    ?,
                    ?
                );
                """;
        try(PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setInt(1, location.getNumber());
            stmt.setString(2, location.getSection());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<Integer, Location> bulkAdd(List<Location> items) {
        HashMap<Integer, Location> locationHashMap = new HashMap<>();

        items.forEach(item -> {
            Integer id = this.add(item);
            if (id != null) locationHashMap.put(item.getId(), item);
        });

        return locationHashMap;
    }

    @Override
    public boolean update(Location location) {
        String sql = "UPDATE Location SET number = ?, section = ? WHERE id = ?";
        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setInt(1, location.getNumber());
            stmt.setString(2, location.getSection());
            stmt.setInt(3, location.getId());

            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer location) {
        String sql = "DELETE FROM Location WHERE id = ?";

        try(PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setInt(1, location);
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Location> findAll() {
        String sql = """
                SELECT
                    l.id AS l_id,
                    l.number AS l_number,
                    l.section AS l_section
                FROM Location l
                """;
        List<Location> locations = new ArrayList<>();

        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                locations.add(ResultToLocation.convert(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }

    @Override
    public Optional<Location> findById(Integer id) {
        String sql = """
            SELECT
                l.id AS l_id,
                l.number AS l_number,
                l.section AS l_section,
                
                a.id AS a_id,
                a.description AS a_description,
                a.employee_reg AS a_employee_reg,
                a.damage AS a_damage,
                a.status AS a_status,
                a.location_id AS a_location_id,
                a.location_status AS a_location_status
            FROM Location l
            LEFT JOIN Asset a ON l.id = a.location_id
            WHERE id = ?
        """;
        Location location = null;

        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                if (location == null) location = ResultToLocation.convert(resultSet);
                location.addAsset(ResultToAsset.convert(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(location);
    }

    @Override
    public Optional<Location> findByLocation(Integer number, String section) {
        String sql = """
            SELECT
                l.id AS l_id,
                l.number AS l_number,
                l.section AS l_section,
                
                a.id AS a_id,
                a.description AS a_description,
                a.employee_reg AS a_employee_reg,
                a.damage AS a_damage,
                a.status AS a_status,
                a.location_id AS a_location_id,
                a.location_status AS a_location_status
            FROM Location l
            LEFT JOIN Asset a ON l.id = a.location_id
            WHERE
                l.number = ? AND
                l.section = ?
        """;
        Location location = null;

        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setInt(1, number);
            stmt.setString(2, section);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                if (location == null) location = ResultToLocation.convert(resultSet);
                location.addAsset(ResultToAsset.convert(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(location);
    }

    @Override
    public boolean haveAssets(List<Asset> assets, Location location) {
        return false;
    }
}
