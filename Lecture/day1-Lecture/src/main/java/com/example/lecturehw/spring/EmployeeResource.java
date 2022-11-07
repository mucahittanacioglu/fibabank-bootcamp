package com.example.lecturehw.spring;

import com.example.lecturehw.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")
public class EmployeeResource {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee")
    public Employee getEmployee(){
        Employee emply = new Employee(121,"Haluk Furya",15000);
        return emply;
    }
    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return employeeService.findAll();
    }
    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable("id") int id){
        return employeeService.find(id);
    }

    @PostMapping("/employee")
    public Employee postEmployee(@RequestBody  Employee empl){
        empl.setEmployeeId(145);
        System.out.println("Employee added: "+empl);
        return empl;
    }
    @PutMapping("/employee")
    public void putEmployee(@RequestBody  Employee empl){
        System.out.println("Employee updated: "+empl);
    }
    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable("id") int id){
        System.out.println("Employee deleted: "+id);
    }
}
