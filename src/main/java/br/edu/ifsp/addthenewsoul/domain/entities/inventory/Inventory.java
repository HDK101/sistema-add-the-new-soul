package br.edu.ifsp.addthenewsoul.domain.entities.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.InventoryStatus;

import java.time.LocalDate;
import java.util.List;

public class Inventory {
    private Integer id;
    private String name;
    private Employee comissionPresident;
    private List<Employee> comission;
    private LocalDate initialDate;
    private LocalDate endDate;
    private List<InventoryAsset> assets;
    private InventoryStatus inventoryStatus;

    public Inventory(Integer id, String name, Employee comissionPresident, List<Employee> comission, LocalDate initialDate, LocalDate endDate, List<InventoryAsset> assets) {
        this.id = id;
        this.name = name;
        this.comissionPresident = comissionPresident;
        this.comission = comission;
        this.initialDate = initialDate;
        this.endDate = endDate;
        this.assets = assets;
        setInventoryAssetsParent();
    }

    public Inventory(String name, LocalDate initialDate, LocalDate endDate, Employee comissionPresident, List<Employee> comission, List<InventoryAsset> assets) {
        this.name = name;
        this.comissionPresident = comissionPresident;
        this.comission = comission;
        this.initialDate = initialDate;
        this.endDate = endDate;
        this.assets = assets;
        setInventoryAssetsParent();

        if (!hasUnverifiedAssets()) throw new IllegalArgumentException("Some assets were already verified");
    }

    private void setInventoryAssetsParent() {
        this.assets.forEach(asset -> asset.setInventory(this));
    }

    public boolean hasUnverifiedAssets() {
        for (InventoryAsset asset : assets) {
            if (asset.getStatus().equals(Status.VERIFIED))
                return false;
        }
        return true;
    }

    public void leaveAssetsAsVerified () {
        for (InventoryAsset asset : assets) {
            asset.setStatus(Status.VERIFIED);
        }
    }

    private void setEmployeeRolesToNormal() {
        for (Employee employee : comission) {
            employee.setRole(Role.EMPLOYEE);
        }
    }

    public void finish() {
        comissionPresident.setRole(Role.EMPLOYEE);
        setEmployeeRolesToNormal();
        setInventoryStatus(InventoryStatus.CLOSED);
    }

    public List<Employee> getComission() {
        return comission;
    }

    public void setComission(List<Employee> comission) {
        this.comission = comission;
    }

    public List<InventoryAsset> getAssets() {
        return assets;
    }

    public void setAssets(List<InventoryAsset> assets) {
        this.assets = assets;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getComissionPresident() {
        return comissionPresident;
    }

    public void setComissionPresident(Employee comissionPresident) {
        this.comissionPresident = comissionPresident;
    }

    public List<Employee> getTeam() {
        return comission;
    }

    public void setTeam(List<Employee> team) {
        this.comission = team;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public InventoryStatus getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(InventoryStatus inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comissionPresident=" + comissionPresident +
                ", comission=" + comission +
                ", initialDate=" + initialDate +
                ", endDate=" + endDate +
                ", assets=" + assets +
                ", status=" + inventoryStatus +
                '}';
    }
}
