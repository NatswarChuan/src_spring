package com.example.demo.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "rls_users", schema = "relationship")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "user_id")
    private UUID id;
    
    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "refresh_token")
    private UUID refreshToken;
}