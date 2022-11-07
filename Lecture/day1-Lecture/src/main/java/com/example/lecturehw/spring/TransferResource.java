package com.example.lecturehw.spring;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferResource {
    @GetMapping("/transfer/header")
    public ResponseEntity<?> getHeader(
            @RequestHeader(value="input",defaultValue = "Empty") String input
    ){
        String output = "Input: "+input;
        return ResponseEntity.ok(input);
    }
    @GetMapping("transfer/setheader")
    public ResponseEntity<?> setHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("input","Test");
        return ResponseEntity
                .ok()
                .headers(headers).build();
    }
}
