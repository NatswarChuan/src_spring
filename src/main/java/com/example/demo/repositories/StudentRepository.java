package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {
    List<Student> findByIdIn(List<String> ids);
}
