package com.example.demo.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, UUID> {
}
