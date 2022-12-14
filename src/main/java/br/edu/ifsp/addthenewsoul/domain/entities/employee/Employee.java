package br.edu.ifsp.addthenewsoul.domain.entities.employee;

import br.edu.ifsp.addthenewsoul.application.io.CSVNode;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import lombok.Builder;

import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Builder
public class Employee implements CSVNode {
    public String name;
    private String registrationNumber;
    private String hashPassword;
    private String virtualPassword;
    private String email;
    private String phone;
    private EnumSet<Role> roles;
    private String rolesDescription;
    private List<Asset> assetsInCharge;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public EnumSet<Role> getRoles() {
        return roles;
    }

    public boolean hasRole(Role role) {
        return roles.contains(role);
    }

    public void addRole(Role role) {
        this.roles.add(role);
        updateRoleDescription();
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
        updateRoleDescription();
    }

    private void updateRoleDescription() {
        this.rolesDescription =
                this.roles.stream()
                        .map(Role::getName)
                        .collect(Collectors.joining(","));
    }

    public String getVirtualPassword() {
        return virtualPassword;
    }

    public void setVirtualPassword(String virtualPassword) {
        this.virtualPassword = virtualPassword;
    }

    public List<Asset> getAssetsInCharge() {
        return assetsInCharge;
    }

    public void setAssetsInCharge(List<Asset> assetsInCharge) {
        this.assetsInCharge = assetsInCharge;
    }

    public String getRolesDescription() {
        return rolesDescription;
    }

    public void setRolesDescription(String rolesDescription) {
        this.rolesDescription = rolesDescription;
    }

    @Override
    public String toCSV() {
        StringBuilder builder = new StringBuilder();

        builder
                .append(name)
                .append(',')
                .append(registrationNumber)
                .append(',')
                .append(hashPassword)
                .append(',')
                .append(email)
                .append(',')
                .append(phone);

        return builder.toString();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("name='").append(name).append('\'');
        sb.append(", registrationNumber='").append(registrationNumber).append('\'');
        sb.append(", hashPassword='").append(hashPassword).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", role=").append(roles);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return registrationNumber.equals(employee.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber);
    }
}
