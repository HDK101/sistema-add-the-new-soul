package br.edu.ifsp.addthenewsoul.domain.entities.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import java.time.LocalDate;
import java.util.List;

public class Inventory {
    private String name;
    private Employee comissionPresident;
    private List<Employee> team;
    private LocalDate initialDate;
    private LocalDate endDate;
    private List<InventoryGood> goods;

    public Inventory(String name, Employee presidenteComissao, List<Employee> team, LocalDate initialDate, LocalDate endDate, List<InventoryGood> goods) {
        this.name = name;
        this.comissionPresident = presidenteComissao;
        this.team = team;
        this.initialDate = initialDate;
        this.endDate = endDate;
        this.goods = goods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getPresidenteComissao() {
        return comissionPresident;
    }

    public void setPresidenteComissao(Employee comissionPresident) {
        this.comissionPresident = comissionPresident;
    }

    public List<Employee> getTeam() {
        return team;
    }

    public void setTeam(List<Employee> team) {
        this.team = team;
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

    public List<InventoryGood> getGoods() {
        return goods;
    }

    public void setGoods(List<InventoryGood> goods) {
        this.goods = goods;
    }
}
