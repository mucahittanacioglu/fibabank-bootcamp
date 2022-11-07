package com.example.lecturehw.spring;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusResource {

    @GetMapping("/status/ok/{id}")
    public ResponseEntity<?> getOk(@PathVariable("id") int employeeId){
        Employee emply = new Employee(employeeId,"Haluk Furya",15000);
        /*
        return new ResponseEntity<>(emply, HttpStatus.OK);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(empty);
        return ResponseEntity.
                ok()
                .body(empty);
         */
        return ResponseEntity.ok(emply);
    }
    @GetMapping("/status/notfound/ok/{id}")
    public ResponseEntity<?> getNotFound(@PathVariable("id") int employeeId){
        Employee emply = new Employee(employeeId,"Haluk Furya",15000);
        /*
        return new ResponseEntity<>(emply, HttpStatus.NOT_FOUND);
        return ResponseEntity
                .status(HttpStatus.NotFound)
                .body("employee not found.");
        return ResponseEntity.
                ok()
                .body("employee not found.");
         */
        return ResponseEntity.notFound().build();
    }
}
