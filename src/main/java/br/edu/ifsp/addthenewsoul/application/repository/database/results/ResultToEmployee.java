package br.edu.ifsp.addthenewsoul.application.repository.database.results;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultToEmployee {
    public static Employee convert(ResultSet rs) throws SQLException {
        return Employee.builder()
                .registrationNumber(rs.getString("e_registration_number"))
                .email(rs.getString("e_email"))
                .phone(rs.getString("e_phone"))
                .hashPassword("e_hash_password")
                .name(rs.getString("e_name"))
                .build();
    }
}
