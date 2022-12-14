package br.edu.ifsp.addthenewsoul.application.repository.database.results;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultToInventoryAsset {
    public static InventoryAsset convert(ResultSet rs) throws SQLException {
        return InventoryAsset.builder()
                .id(rs.getInt("ia_id"))
                .description(rs.getString("ia_description"))
                .damage(rs.getString("ia_damage"))
                .value(rs.getDouble("ia_value"))
                .status(Status.valueOf(rs.getString("ia_status")))
                .build();
    }
}
