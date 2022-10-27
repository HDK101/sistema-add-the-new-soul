package br.edu.ifsp.addthenewsoul.domain.entities.asset;

import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import com.opencsv.bean.CsvBindByName;

public class Asset {
    private int id;
    private String description;
    private Employee employeeInCharge;
    private double value;
    private String damage;
    private Local location;

    public Asset() {}

    public Asset(int id, String description, Employee employeeInCharge, double value, String damage, Local location) {
        this.id = id;
        this.description = description;
        this.employeeInCharge = employeeInCharge;
        this.value = value;
        this.damage = damage;
        this.location = location;
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

    public Local getLocation() {
        return location;
    }

    public void setLocation(Local location) {
        this.location = location;
    }
}
