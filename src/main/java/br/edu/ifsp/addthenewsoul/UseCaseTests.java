package br.edu.ifsp.addthenewsoul;

import br.edu.ifsp.addthenewsoul.application.repository.inMemory.InMemoryAssetDAO;
import br.edu.ifsp.addthenewsoul.application.repository.inMemory.InMemoryEmployeeDAO;
import br.edu.ifsp.addthenewsoul.application.repository.inMemory.InMemoryInventoryDAO;
import br.edu.ifsp.addthenewsoul.application.repository.inMemory.InMemoryLocationDAO;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AddAssetUseCase;
import br.edu.ifsp.addthenewsoul.domain.usecases.asset.AssetDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.employee.*;
import br.edu.ifsp.addthenewsoul.domain.usecases.inventory.InventoryDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.location.LocationDAO;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.Session;

public class UseCaseTests {
    public static void main(String[] args) {
        AssetDAO assetDAO = new InMemoryAssetDAO();
        EmployeeDAO employeeDAO = new InMemoryEmployeeDAO();
        LocationDAO locationDAO = new InMemoryLocationDAO();
        InventoryDAO inventoryDAO = new InMemoryInventoryDAO();

        AddAssetUseCase addAssetUseCase = new AddAssetUseCase(assetDAO);

        addAssetUseCase.save(new Asset("Cadeira", 50.0, "Nenhum"));
        addAssetUseCase.save(new Asset("Mesa", 200.0, "Nenhum"));
        addAssetUseCase.save(new Asset("Computador Desktop", 2000.0, "Nenhum"));

        //Implementar editar bens

        //Implementar dar baixa no bem

        AddEmployeeUseCase addEmployeeUseCase = new AddEmployeeUseCase(employeeDAO);
        Employee employee1 = new Employee("Walter", "R12345", "senha123", "walter@email.com", "(18) 99999-9999", Role.EXECUTOR);
        Employee employee2 = new Employee("Eisen", "R12346", "senha123", "eisen@email.com", "(18) 99999-9999", Role.EXECUTOR);
        addEmployeeUseCase.save(employee1);
        addEmployeeUseCase.save(employee2);

        UpdateEmployeeUseCase updateEmployeeUseCase = new UpdateEmployeeUseCase(employeeDAO);
        employee1.setEmail("walter@gmail.com");
        updateEmployeeUseCase.update(employee1);

        RemoveEmployeeUseCase removeEmployeeUseCase = new RemoveEmployeeUseCase(employeeDAO);
        removeEmployeeUseCase.remove("R12346");

        LoginEmployeeUseCase loginEmployeeUseCase = new LoginEmployeeUseCase(employeeDAO);

        Employee employee = loginEmployeeUseCase.login("walter@gmail.com", "senha123");
        System.out.println(employee);
        System.out.println(Session.getInstance().getLoggedUser());
        System.out.println(Session.getInstance().getLoggedUser() == employee);

        LogoutEmployeeUseCase logoutEmployeeUseCase = new LogoutEmployeeUseCase();
        logoutEmployeeUseCase.logout();

        System.out.println(Session.getInstance().getLoggedUser() == null);

        System.out.println(assetDAO.findAll());
    }
}
