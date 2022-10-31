package br.edu.ifsp.addthenewsoul.domain.entities.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
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

    public Inventory(Integer id, String name, Employee comissionPresident, List<Employee> comission, LocalDate initialDate, LocalDate endDate, List<InventoryAsset> assets) {
        this.id = id;
        this.name = name;
        this.comissionPresident = comissionPresident;
        this.comission = comission;
        this.initialDate = initialDate;
        this.endDate = endDate;
        this.assets = assets;
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

    public List<InventoryAsset> getassets() {
        return assets;
    }

    public void setassets(List<InventoryAsset> assets) {
        this.assets = assets;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "name='" + name + '\'' +
                ", comissionPresident=" + comissionPresident +
                ", comission=" + comission +
                ", initialDate=" + initialDate +
                ", endDate=" + endDate +
                ", assets=" + assets +
                '}';
    }
}
