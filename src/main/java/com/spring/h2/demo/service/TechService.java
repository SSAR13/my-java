package com.spring.h2.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.spring.h2.demo.model.TechDetailsEntity;
import com.spring.h2.demo.repository.TechRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.h2.demo.exception.RecordNotFoundException;

@Service
public class TechService {
     
    @Autowired
    TechRepository repository;
     
    public List<TechDetailsEntity> getAllEmployees()
    {
        List<TechDetailsEntity> employeeList = repository.findAll();
         
        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<TechDetailsEntity>();
        }
    }
     
    public TechDetailsEntity getEmployeeById(Long id) throws RecordNotFoundException
    {
        Optional<TechDetailsEntity> employee = repository.findById(id);
         
        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
     
    public TechDetailsEntity createOrUpdateEmployee(TechDetailsEntity entity) throws RecordNotFoundException
    {
        Optional<TechDetailsEntity> employee = repository.findById(entity.getId());
         
        if(employee.isPresent())
        {
            TechDetailsEntity newEntity = employee.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
 
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
            entity = repository.save(entity);
             
            return entity;
        }
    }
     
    public void deleteEmployeeById(Long id) throws RecordNotFoundException
    {
        Optional<TechDetailsEntity> employee = repository.findById(id);
         
        if(employee.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}