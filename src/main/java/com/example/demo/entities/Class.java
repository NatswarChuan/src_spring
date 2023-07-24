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
@Table(name = "rls_classes", schema = "relationship")
public class Class implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "class_id")
    private UUID id;

    @Column(name = "class_name")
    private String name;

    @ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

    @ManyToMany(targetEntity = Student.class)
	@JoinTable(name = "rls_students_classes", schema = "relationship", joinColumns = {
			@JoinColumn(name = "class_id", referencedColumnName = "class_id") }, inverseJoinColumns = {
					@JoinColumn(name = "student_id", referencedColumnName = "student_id") })
    private List<Student> students;
}