package br.edu.ifsp.addthenewsoul.domain.entities.asset;

import br.edu.ifsp.addthenewsoul.application.io.CSVNode;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import br.edu.ifsp.addthenewsoul.domain.entities.inventory.Status;

import java.util.Optional;

public class Asset implements CSVNode {
    private int id;
    private String description;
    private Employee employeeInCharge;
    private double value;
    private String damage;
    private Location location;
    private Status status;

    public Asset() {}

    public Asset(int id, String description, double value, String damage) {
        this.id = id;
        this.description = description;
        this.employeeInCharge = employeeInCharge;
        this.value = value;
        this.damage = damage;
        this.location = location;
    }

    public Asset(int id, String description, Employee employeeInCharge, double value, String damage, Location location) {
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Asset{");
        sb.append("id=").append(id);
        sb.append(", description='").append(description).append('\'');
        sb.append(", employeeInCharge=").append(employeeInCharge.getRegistrationNumber());
        sb.append(", value=").append(value);
        sb.append(", damage='").append(damage).append('\'');
        sb.append(", location=").append(location.fullLocation());
        sb.append('}');
        return sb.toString();
    }

    public String toCSV() {
        StringBuilder builder = new StringBuilder();
/*
        builder
            .append(id)
            .append(',')
            .append(description)
            .append(',')
            .append(employeeInCharge != null ? employeeInCharge.getRegistrationNumber() : "null")
            .append(',')
            .append(value)
            .append(',')
            .append(damage)
            .append(',')
            .append(location != null ? location.getId() : "null");
 */

        builder
                .append(id)
                .append(',')
                .append(description)
                .append(',')
                .append(value)
                .append(',')
                .append(damage);

        return builder.toString();
    }
}
