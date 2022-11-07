package com.example.lecturehw.services;

import com.example.lecturehw.spring.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeImpl implements EmployeeService {
    @Override
    public Employee find(int id) {
        Employee employee = new Employee(id, "Mehmet Gürler",10400);
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(121,"Haluk Furya",15000));
        employeeList.add(new Employee(101,"Hulisi Mazgil",25000));
        employeeList.add(new Employee(131,"Barış Turan",5000));
        return employeeList;
    }
}
