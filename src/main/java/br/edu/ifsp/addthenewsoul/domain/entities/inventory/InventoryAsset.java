package br.edu.ifsp.addthenewsoul.domain.entities.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;

public class InventoryAsset {
    private Asset asset;
    private Integer id;
    private String description;
    private Employee employeeInCharge;
    private double value;
    private String damage;
    private Location location;
    private Status status;
    private Inventory inventory;

    public InventoryAsset(Asset asset, String description, Employee employeeInCharge, double value, String damage, Location location) {
        this.asset = asset;
        this.description = description;
        this.employeeInCharge = employeeInCharge;
        this.value = value;
        this.damage = damage;
        this.location = location;
    }

    public static InventoryAsset createFromAsset(Asset asset) {
        return new InventoryAsset(
                asset,
                asset.getDescription(),
                asset.getEmployeeInCharge(),
                asset.getValue(),
                asset.getDamage(),
                asset.getLocation()
        );
    }

    public void applyDamage(String damage) {
        this.damage = damage;
        this.asset.setDamage(damage);
    }

    public Asset getAsset() {
        return asset;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Employee getEmployeeInCharge() {
        return employeeInCharge;
    }

    public double getValue() {
        return value;
    }

    public String getDamage() {
        return damage;
    }

    public Location getLocation() {
        return location;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
