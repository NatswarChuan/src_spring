package com.example.demo.entities;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {
	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "shop_id")
	@JsonIgnore
	private Shop shop;

	@Id
	@Column(name = "id", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	/**
	 * time create entity can't update this column
	 */
	@Column(name = "create_date", updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonProperty(value = "create_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date createDate;

	/**
	 * last time update entity update this column each time update entity
	 */
	@Column(name = "update_date")
	@JsonProperty(value = "update_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date updateDate;

	@ManyToMany(targetEntity = Category.class)
	@JoinTable(name = "products_categories", 
			joinColumns={@JoinColumn(name="product_id", referencedColumnName="id")}, 
            inverseJoinColumns={@JoinColumn(name="category_id", referencedColumnName="id")})
	private List<Category> categories;
}
