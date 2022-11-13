package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table(name = "mst_student")
@EqualsAndHashCode
public class MstStudent {

	@Id
	@Column(name = "student_id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long studentId;

	@Column(name = "student_name")
	private String studentName;

	@Column(name = "student_sex")
	private Integer studentSex;

	@Column(name = "student_age")
	private Integer studentAge;

	@Column(name = "data_status", columnDefinition = "integer default 0")
	private Integer dataStatus;

	@Column(name = "registered_date", updatable = false)
	private Date registeredDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	@ManyToMany(mappedBy = "mstStudentList")
	private List<MstClass> mstCLassList;
}
