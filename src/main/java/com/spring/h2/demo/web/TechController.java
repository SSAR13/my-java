package com.spring.h2.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.h2.demo.exception.RecordNotFoundException;
import com.spring.h2.demo.model.TechDetailsEntity;
import com.spring.h2.demo.service.TechService;
 
@RestController
@RequestMapping("/employees")
public class TechController
{
    @Autowired
    TechService service;
 
    @GetMapping
    public ResponseEntity<List<TechDetailsEntity>> getAllEmployees() {
        List<TechDetailsEntity> list = service.getAllEmployees();
 
        return new ResponseEntity<List<TechDetailsEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<TechDetailsEntity> getEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        TechDetailsEntity entity = service.getEmployeeById(id);
 
        return new ResponseEntity<TechDetailsEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<TechDetailsEntity> createOrUpdateEmployee(TechDetailsEntity employee)
                                                    throws RecordNotFoundException {
        TechDetailsEntity updated = service.createOrUpdateEmployee(employee);
        return new ResponseEntity<TechDetailsEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deleteEmployeeById(id);
        return HttpStatus.FORBIDDEN;
    }
 
}