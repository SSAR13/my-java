package com.spring.h2.demo.repository;

import com.spring.h2.demo.model.TechDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechRepository
        extends JpaRepository<TechDetailsEntity, Long> {
 
}
