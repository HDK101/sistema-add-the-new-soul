package br.edu.ifsp.addthenewsoul.application.repository.database.results;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultToLocation {
    public static Location convert(ResultSet rs) throws SQLException {
        return Location.builder()
                .id(rs.getInt("l_id"))
                .number(rs.getInt("l_number"))
                .section(rs.getString("l_section"))
                .build();
    }
}
