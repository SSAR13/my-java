package com.spring.h2.demo.web;

import java.util.List;

import com.spring.h2.demo.model.TechDetailsEntity;
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
import com.spring.h2.demo.service.TechService;
 
@RestController
@RequestMapping("/tech")
public class TechController
{
    @Autowired
    TechService service;
 
    @GetMapping(value = "/all")
    public ResponseEntity<List<TechDetailsEntity>> getAllTechs() {
        List<TechDetailsEntity> list = service.getAllTechs();
 
        return new ResponseEntity<List<TechDetailsEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<TechDetailsEntity> getTechById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        TechDetailsEntity entity = service.getTechById(id);
 
        return new ResponseEntity<TechDetailsEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<TechDetailsEntity> createOrUpdateTech(TechDetailsEntity techDetails)
                                                    throws RecordNotFoundException {
        TechDetailsEntity updated = service.createOrUpdateTech(techDetails);
        return new ResponseEntity<TechDetailsEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteTechById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deleteTechById(id);
        return HttpStatus.FORBIDDEN;
    }
 
}