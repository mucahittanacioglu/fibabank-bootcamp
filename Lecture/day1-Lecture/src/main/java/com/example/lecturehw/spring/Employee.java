package com.example.lecturehw.spring;

public class Employee {
    private int employeeId;
    private String employeeName;
    private double monthlySalary;

    public Employee() {
    }

    public Employee(int employeeId, String employeeName, double monthlySalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.monthlySalary = monthlySalary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }



    @Override
    public String toString() {
        return this.employeeId +" "+this.employeeName+ " "+ this.monthlySalary;
    }
}
