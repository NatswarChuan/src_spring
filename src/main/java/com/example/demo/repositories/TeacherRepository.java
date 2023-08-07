package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Teacher;

@Repository
public interface TeacherRepository extends MongoRepository<Teacher,String>{
    
}
