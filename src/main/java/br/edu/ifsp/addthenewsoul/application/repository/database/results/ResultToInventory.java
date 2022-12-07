package br.edu.ifsp.addthenewsoul.application.repository.database.results;

import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.InventoryStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResultToInventory {
    public static Inventory convert(ResultSet rs) throws SQLException {
        return Inventory.builder()
                .id(rs.getString("i_id"))
                .name(rs.getString("i_name"))
                .initialDate(rs.getDate("i_initial_date").toLocalDate())
                .endDate(rs.getDate("i_end_date").toLocalDate())
                .assets(new ArrayList<>())
                .comission(new ArrayList<>())
                .inventoryStatus(InventoryStatus.valueOf(rs.getString("i_inventory_status")))
                .build();
    }
}
