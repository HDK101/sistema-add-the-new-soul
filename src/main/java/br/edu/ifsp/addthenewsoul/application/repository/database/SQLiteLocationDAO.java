package br.edu.ifsp.addthenewsoul.application.repository.database;

import br.edu.ifsp.addthenewsoul.application.repository.database.results.ResultToLocation;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.LocationDAO;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SQLiteLocationDAO implements LocationDAO {

    @Override
    public Integer add(Location location) {
        return null;
    }

    @Override
    public Map<Integer, Location> bulkAdd(List<Location> items) {
        return null;
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
        return false;
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
            if (resultSet.next()) {
                locations.add(ResultToLocation.convert(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }

    @Override
    public Optional<Location> findById(Integer id) {
        String sql = "SELECT * FROM Location WHERE id = ?";
        Location location = null;

        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                location = ResultToLocation.convert(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(location);
    }

    @Override
    public Optional<Location> findByLocation(Integer number, String section) {
        return Optional.empty();
    }

    @Override
    public boolean haveAssets(List<Asset> assets, Location location) {
        return false;
    }
}
