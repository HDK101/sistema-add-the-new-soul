package br.edu.ifsp.addthenewsoul.domain.usecases.employee;

public class RemoveEmployeeUseCase {

    private EmployeeDAO employeeDAO;

    public RemoveEmployeeUseCase(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public boolean remove(String registrationNumber) {
        if (registrationNumber == null || employeeDAO.findByRegistrationNumber(registrationNumber).isEmpty())
            throw new IllegalArgumentException("Employee not found.");

        return employeeDAO.delete(registrationNumber);
    }
}
