package com.example.demo.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "rls_teachers", schema = "relationship")
public class Teacher implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "teacher_id")
    private UUID id;

    @Column(name = "teacher_name")
    private String name;

    @OneToMany(mappedBy="teacher",targetEntity=Class.class, cascade = CascadeType.ALL)
	private List<Class> classes;
}