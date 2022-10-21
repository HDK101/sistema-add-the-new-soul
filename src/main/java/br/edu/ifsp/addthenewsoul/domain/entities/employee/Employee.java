package br.edu.ifsp.addthenewsoul.domain.entities.employee;

public class Employee {
    private String name;
    private String registrationNumber;
    private String hashPassword;
    private String email;
    private String phone;
    private Role role;

    public Employee(String name, String registrationNumber, String hashPassword, String email, String phone, Role role) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.hashPassword = hashPassword;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
