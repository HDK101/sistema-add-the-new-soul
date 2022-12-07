package br.edu.ifsp.addthenewsoul.domain.entities.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Role;
import br.edu.ifsp.addthenewsoul.domain.usecases.utils.InventoryStatus;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Builder
public class Inventory {
    private String id;
    private String name;
    private Employee comissionPresident;
    private List<Employee> comission;
    private LocalDate initialDate;
    private LocalDate endDate;
    private List<InventoryAsset> assets;
    private InventoryStatus inventoryStatus;

    public void setInventoryAssetsParent() {
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
            employee.removeRole(Role.EXECUTOR);
        }
    }

    public void finish() {
        comissionPresident.removeRole(Role.CHAIRMAN_OF_THE_COMISSION);
        setEmployeeRolesToNormal();
        setInventoryStatus(InventoryStatus.CLOSED);
    }

    public boolean hasEmployeeInCommision(Employee employee) {
        return this.comission.contains(employee);
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

    public void addAsset(InventoryAsset asset) {
        this.assets.add(asset);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public boolean hasComissionPresident(Employee comissionPresident) {
        return this.comissionPresident.equals(comissionPresident);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return id.equals(inventory.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
