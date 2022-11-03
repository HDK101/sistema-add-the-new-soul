package br.edu.ifsp.addthenewsoul.domain.usecases.report;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Inventory;

import java.io.IOException;

public interface InventoryReportWriter {
    void write(Inventory inventory) throws IOException;
}
