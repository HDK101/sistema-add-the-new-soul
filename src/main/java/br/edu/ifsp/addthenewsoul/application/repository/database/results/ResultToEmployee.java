package br.edu.ifsp.addthenewsoul.application.repository.database.results;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.InventoryAsset;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EnumSet;

public class ResultToEmployee {
    public static Employee convert(ResultSet rs) throws SQLException {
        return Employee.builder()
                .registrationNumber(rs.getString("e_registration_number"))
                .email(rs.getString("e_email"))
                .phone(rs.getString("e_phone"))
                .hashPassword(rs.getString("e_hash_password"))
                .name(rs.getString("e_name"))
                .roles(EnumSet.noneOf(Role.class))
                .rolesDescription("")
                .build();
    }
}
