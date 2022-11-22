package com.example.demo.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "shops")
@Data
public class Shop {
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy="shop",targetEntity=Product.class, cascade = CascadeType.REMOVE)
	private List<Product> products;
	
	@Id
	@Column(name="id",updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	/**
	 * time create entity
	 * can't update this column
	 */
	@Column(name="create_date",updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty(value="create_date")
	protected Date createDate;

	/**
	 * last time update entity
	 * update this column each time update entity
	 */
	@Column(name="update_date")
	@JsonProperty(value="update_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date updateDate;
}
