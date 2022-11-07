package com.example.lecturehw.web;

import com.example.lecturehw.spring.Employee;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class EmployeeController {
    @GetMapping("/employee/get")
    @ResponseBody
    public Employee getEmployee(){
        int id =100;
        String url = "http://localhost:8080/api/employee/"+id;

        RestTemplate restTemplate = new RestTemplate();
        Employee empl = restTemplate.getForObject(url,Employee.class);
        System.out.println("Employee: "+ empl.getEmployeeName());
        return empl;
    }

    @GetMapping("/employee/post")
    @ResponseBody
    public String postEmployee(){
        Employee empl = new Employee(0,"Furkan Yolar",10000);
        String url = "http://localhost:8080/api/employee/";

        RestTemplate restTemplate = new RestTemplate();
        Employee res = restTemplate.postForObject(url,empl,Employee.class);
        System.out.println("Employee posted: "+ res.getEmployeeName());
        return "Employee posted: "+ res;
    }

    @GetMapping("/employee/put")
    @ResponseBody
    public String putEmployee(){
        Employee empl = new Employee(0,"Furkan Yolar",10000);
        String url = "http://localhost:8080/api/employee/";

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(empl), Void.class);
        System.out.println("Employee update success: "+ empl);
        return "Employee updated: "+ empl;
    }
    @GetMapping("/employee/delete")
    @ResponseBody
    public String deleteEmployee(){
        int id = 152;
        String url = "http://localhost:8080/api/employee/"+id;

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url);
        System.out.println("Employee deleted: "+ id);
        return "Employee deleted: "+ id;
    }
}
