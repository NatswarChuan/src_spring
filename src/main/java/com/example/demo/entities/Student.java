package com.example.demo.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
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

    @ManyToMany(targetEntity = Class.class)
	@JoinTable(name = "rls_students_classes", schema = "relationship", 
			joinColumns={@JoinColumn(name="student_id", referencedColumnName="student_id")}, 
            inverseJoinColumns={@JoinColumn(name="class_id", referencedColumnName="class_id")})
    private List<Class> classes;
}