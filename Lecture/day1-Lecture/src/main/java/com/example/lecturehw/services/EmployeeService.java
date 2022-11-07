package com.example.lecturehw.services;

import com.example.lecturehw.spring.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    Employee find(int id);
    List<Employee> findAll();
}
