package br.edu.ifsp.addthenewsoul.application.database;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Local;
import br.edu.ifsp.addthenewsoul.domain.usecases.local.LocalDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SQLiteLocalDAO implements LocalDAO {

    @Override
    public Integer add(Local local) {
        return null;
    }

    @Override
    public boolean update(Local local) {
        String sql = "UPDATE Local SET number = ?, section = ? WHERE id = ?";
        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setInt(1, local.getNumber());
            stmt.setString(2, local.getSection());
            stmt.setInt(3, local.getId());

            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer local) {
        return false;
    }

    @Override
    public List<Local> findAll() {
        return null;
    }

    @Override
    public Optional<Local> findById(Integer id) {
        String sql = "SELECT * FROM Local WHERE id = ?";
        Local local = null;

        try (PreparedStatement stmt = Database.createPreparedStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                local = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(local);
    }

    @Override
    public Optional<Local> findByLocation(Integer number, String section) {
        return Optional.empty();
    }

    private Local resultSetToEntity(ResultSet rs) throws SQLException {
        return new Local(
                            rs.getInt("id"),
                            rs.getInt("number"),
                            rs.getString("section")
        );
    }

}
