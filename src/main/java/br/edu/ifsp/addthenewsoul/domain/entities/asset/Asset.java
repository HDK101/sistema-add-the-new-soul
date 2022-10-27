package br.edu.ifsp.addthenewsoul.domain.entities.asset;

import br.edu.ifsp.addthenewsoul.application.io.CSVNode;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;

public class Asset implements CSVNode {
    private int id;
    private String description;
    private Employee employeeInCharge;
    private String registrationNumber;
    private double value;
    private String damage;
    private Local location;
    private Integer localId;

    public Asset() {}

    public Asset(int id, String description, String registrationNumber, double value, String damage, Integer localId) {
        this.id = id;
        this.description = description;
        this.registrationNumber = registrationNumber;
        this.value = value;
        this.damage = damage;
        this.localId = localId;
    }

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

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Integer getLocalId() {
        return localId;
    }

    public void setLocalId(Integer localId) {
        this.localId = localId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Asset{");
        sb.append("id=").append(id);
        sb.append(", description='").append(description).append('\'');
        sb.append(", employeeInCharge=").append(employeeInCharge);
        sb.append(", registrationNumber='").append(registrationNumber).append('\'');
        sb.append(", value=").append(value);
        sb.append(", damage='").append(damage).append('\'');
        sb.append(", location=").append(location);
        sb.append(", localId=").append(localId);
        sb.append('}');
        return sb.toString();
    }

    public String toCSV() {
        StringBuilder builder = new StringBuilder();

        builder
            .append(id)
            .append(',')
            .append(description)
            .append(',')
            .append(employeeInCharge.getRegistrationNumber())
            .append(',')
            .append(value)
            .append(',')
            .append(damage)
            .append(',')
            .append(1);
        //Trocar pro id do local

        return builder.toString();
    }
}
