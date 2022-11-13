package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "mst_class")
@EqualsAndHashCode
public class MstClass {

	@Id
	@Column(name = "class_id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long classId;

	@Column(name = "class_name")
	private String className;

	@Column(name = "data_status")
	private Integer dataStatus = 0;

	@Column(name = "registered_date", updatable = false)
	private Date registeredDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	@ManyToMany(targetEntity = MstStudent.class)
	@JoinTable(name = "trn_enrollement", joinColumns = @JoinColumn(name = "class_id", nullable = false), 
	inverseJoinColumns = @JoinColumn(name = "student_id", nullable = false))
	private List<MstStudent> mstStudentList;
}
