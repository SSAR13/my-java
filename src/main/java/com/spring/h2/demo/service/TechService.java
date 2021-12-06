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
     
    public List<TechDetailsEntity> getAllTechs()
    {
        List<TechDetailsEntity> techList = repository.findAll();
         
        if(techList.size() > 0) {
            return techList;
        } else {
            return new ArrayList<TechDetailsEntity>();
        }
    }
     
    public TechDetailsEntity getTechById(Long id) throws RecordNotFoundException
    {
        Optional<TechDetailsEntity> tech = repository.findById(id);
         
        if(tech.isPresent()) {
            return tech.get();
        } else {
            throw new RecordNotFoundException("No tech record exist for given id");
        }
    }
     
    public TechDetailsEntity createOrUpdateTech(TechDetailsEntity entity) throws RecordNotFoundException
    {
        Optional<TechDetailsEntity> tech = repository.findById(entity.getId());
         
        if(tech.isPresent())
        {
            TechDetailsEntity newEntity = tech.get();
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
     
    public void deleteTechById(Long id) throws RecordNotFoundException
    {
        Optional<TechDetailsEntity> tech = repository.findById(id);
         
        if(tech.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No tech record exist for given id");
        }
    }
}