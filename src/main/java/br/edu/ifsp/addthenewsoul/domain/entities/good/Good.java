package br.edu.ifsp.addthenewsoul.domain.entities.good;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;

public class Good {
    private int id;
    private String description;
    private Employee employeeInCharge;
    private double value;
    private String damage;

    public Good() {}

    public Good(int id, String description, Employee employeeInCharge, double value, String damage) {
        this.id = id;
        this.description = description;
        this.employeeInCharge = employeeInCharge;
        this.value = value;
        this.damage = damage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getEmployeeInCharge() {
        return employeeInCharge;
    }

    public void setEmployeeInCharge(Employee employeeInCharge) {
        this.employeeInCharge = employeeInCharge;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }
}
