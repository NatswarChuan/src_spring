package com.example.demo.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    List<Student> findByIdIn(List<UUID> studentIds);
}
