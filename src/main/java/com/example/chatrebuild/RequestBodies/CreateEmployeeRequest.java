package com.example.chatrebuild.RequestBodies;

public class CreateEmployeeRequest {
    private String employeeName;
    private String employeePassword;
    private String employeeEmail;
    private String employeeRole;

    public CreateEmployeeRequest(String employeeName, String employeePassword, String employeeEmail, String employeeRole) {
        this.employeeName = employeeName;
        this.employeePassword = employeePassword;
        this.employeeEmail = employeeEmail;
        this.employeeRole = employeeRole;
    }

    public CreateEmployeeRequest() {
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }
}
