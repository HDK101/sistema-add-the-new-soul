package br.edu.ifsp.addthenewsoul.application.repository.database.results;

import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultToInventory {
    public static Inventory convert(ResultSet rs) throws SQLException {
        return Inventory.builder()
//                .id(rs.getInt("ia_id"))
//                .description(rs.getString("ia_description"))
//                .damage(rs.getString("ia_damage"))
//                .status(Status.valueOf(rs.getString("ia_status")))
                .build();
    }
}
