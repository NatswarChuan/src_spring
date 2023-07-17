package com.example.demo.Repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entities.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, UUID> {
}
