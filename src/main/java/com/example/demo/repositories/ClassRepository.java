package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Class;

@Repository
public interface ClassRepository extends MongoRepository<Class,String>{
    
}
