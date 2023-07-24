package com.example.demo.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Class;

@Repository
public interface ClassRepository extends JpaRepository<Class, UUID> {
}
