package com.example.demo.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

import lombok.*;

@Data
@Entity
@Table(name = "rls_students",schema = "relationship")
public class Student implements Serializable {
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;
    
    @Column(name = "student_name")
    private String name;
}