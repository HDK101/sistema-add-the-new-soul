package br.edu.ifsp.addthenewsoul.domain.entities.employee;

import br.edu.ifsp.addthenewsoul.application.io.CSVNode;
import br.edu.ifsp.addthenewsoul.domain.entities.asset.Asset;
import lombok.Builder;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Builder
public class Employee implements CSVNode {
    private String name;
    private String registrationNumber;
    private String hashPassword;
    private String virtualPassword;
    private String email;
    private String phone;
    private EnumSet<Role> roles;
    private List<Asset> assetsInCharge;

    /*
    public Employee(String name, String registrationNumber, String virtualPassword, String email, String phone, Role role) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.virtualPassword = virtualPassword;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.assetsInCharge = new ArrayList<>();
    }

    public Employee(String name, String registrationNumber, String email, String phone, Role role) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }
     */

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

    public void setRoles(Role role) {
        this.roles.add(role);
    }

    public void removeRoles (Role role) {
        this.roles.remove(role);
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
                .append(phone)
                .append(',')
                .append(roles.toString());

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
}
