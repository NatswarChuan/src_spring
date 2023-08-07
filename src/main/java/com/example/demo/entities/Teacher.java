package com.example.demo.entities;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "teachers")
public class Teacher {
    private String id; 
    private String name;

    
    @DocumentReference(lookup="{'teacher':?#{#self._id} }")
    private List<Class> classes;
    
}