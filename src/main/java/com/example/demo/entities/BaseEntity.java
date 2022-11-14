package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * base entity
 * 
 * @author natsw
 *
 * @param <ID> type of entity id
 */
@Data
@Entity
public class BaseEntity<ID> {
	/**
	 * id of entity
	 */
	@Id
	@Column(name="id",updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	ID id;

	/**
	 * time create entity
	 * can't update this column
	 */
	@Column(name="create_date",updatable = false)
	Date createDate;

	/**
	 * last time update entity
	 * update this column each time update entity
	 */
	@Column(name="update_date")
	Date updateDate;
}
