package com.spring.h2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.h2.demo.model.EmployeeEntity;
 
@Repository
public interface TechRepository
        extends JpaRepository<EmployeeEntity, Long> {
 
}
