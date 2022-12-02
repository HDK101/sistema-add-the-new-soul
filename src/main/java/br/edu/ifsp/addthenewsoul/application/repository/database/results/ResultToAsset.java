package br.edu.ifsp.addthenewsoul.application.repository.database.results;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.LocationStatus;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultToAsset {
    public static Asset convert(ResultSet rs) throws SQLException {
        return Asset.builder()
                .id(rs.getInt("a_id"))
                .description(rs.getString("a_description"))
                .value(rs.getDouble("a_value"))
                .damage(rs.getString("a_damage"))
                .status(Status.valueOf(rs.getString("a_status")))
                .locationStatus(LocationStatus.valueOf(rs.getString("a_location_status")))
                .build();
    }
}
