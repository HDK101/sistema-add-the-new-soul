package br.edu.ifsp.addthenewsoul.application.database;

import br.edu.ifsp.addthenewsoul.domain.usecases.location.LocationDAO;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SQLiteLocationDAO implements LocationDAO {

    @Override
    public Integer add(Location location) {
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
        return null;
    }

    @Override
    public Optional<Location> findById(Integer id) {
        String sql = "SELECT * FROM Local WHERE id = ?";
        Location location = null;

        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                location = resultSetToEntity(resultSet);
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

    private Location resultSetToEntity(ResultSet rs) throws SQLException {
        return new Location(
                            rs.getInt("id"),
                            rs.getInt("number"),
                            rs.getString("section")
        );
    }

}
