package br.edu.ifsp.addthenewsoul.application.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Local;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.LocationDAO;

public class SQLiteLocationDAO implements LocationDAO {

    @Override
    public Optional<Local> findById(Integer id) {
        String sql = "SELECT * FROM Local where id = ?";
        Local local = null;
        try (PreparedStatement stmt = Database.createPrepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                local = new Local(rs.getInt("id"),
                        rs.getString("section"),
                        rs.getInt("number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(local);
    }

    @Override
    public Object add(Object type) {
        return null;
    }

    @Override
    public boolean delete(Object key) {
        return false;
    }

    @Override
    public List<Local> findAll() {
        return null;
    }

    @Override
    public boolean update(Object type) {
        return false;
    }

}
