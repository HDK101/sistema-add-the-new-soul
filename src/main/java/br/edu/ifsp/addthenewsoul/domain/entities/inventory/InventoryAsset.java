package br.edu.ifsp.addthenewsoul.domain.entities.inventory;

import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Location;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.LocationStatus;
import br.edu.ifsp.addthenewsoul.domain.entities.employee.Employee;
import lombok.Builder;

import java.util.Objects;

@Builder
public class InventoryAsset {
    private Asset asset;
    private Integer id;
    private final String description;
    private final Employee employeeInCharge;
    private Employee inventoryManager;
    private final double value;
    private String damage;
    private final Location location;
    private Status status;
    private Inventory inventory;
    private LocationStatus locationStatus;

    public static InventoryAsset createFromAsset(Asset asset) {
        if (asset.getLocation() == null || asset.getEmployeeInCharge() == null) {
            throw new IllegalStateException("Location and employee in change must be set");
        }

        return InventoryAsset.builder()
                .asset(asset)
                .description(asset.getDescription())
                .value(asset.getValue())
                .damage(asset.getDamage())
                .location(asset.getLocation())
                .build();
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
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

    public void setId(Integer id) {
        this.id = id;
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

    public Employee getInventoryManager() {
        return inventoryManager;
    }

    public void setInventoryManager(Employee inventoryManager) {
        this.inventoryManager = inventoryManager;
    }


    public LocationStatus getLocationStatus() {
        return locationStatus;
    }

    public void setLocationStatus(LocationStatus locationStatus) {
        this.locationStatus = locationStatus;
    }

    public void applyEvaluation(String damage, LocationStatus locationStatus) {
        this.damage = damage;
        this.locationStatus = locationStatus;

        if (asset != null) {
            this.asset.setDamage(damage);
            this.asset.setLocationStatus(locationStatus);
        }
    }

    public boolean hasStatus(Status status) {
        return this.status == status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InventoryAsset{");
        sb.append("asset=").append(asset);
        sb.append(", id=").append(id);
        sb.append(", description='").append(description).append('\'');
        sb.append(", employeeInCharge=").append(employeeInCharge);
        sb.append(", responsible=").append(inventoryManager);
        sb.append(", value=").append(value);
        sb.append(", damage='").append(damage).append('\'');
        sb.append(", location=").append(location);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryAsset that = (InventoryAsset) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
