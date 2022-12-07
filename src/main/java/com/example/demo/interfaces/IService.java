package com.example.demo.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * interface for service
 *
 * @param <E>  type of entity
 * @param <ID> type of entity id
 */
public interface IService<E, ID> {
	/**
	 * get all entity in database
	 * 
	 * @return list entities
	 * @throws Exception
	 */
	public List<E> findAll() throws Exception;

	/**
	 * get all entity in database
	 * 
	 * @return list entities
	 * @throws Exception
	 */
	public Page<E> findAll(int page, int size) throws Exception;

	/**
	 * get entity by id
	 * 
	 * @param id
	 * @return entity
	 * @throws Exception
	 */
	public E findById(ID id) throws Exception;

	/**
	 * insert entity to database
	 * 
	 * @param newEntity
	 * @throws Exception
	 */
	public <S extends IDto<E>> void create(S newEntity) throws Exception;

	/**
	 * update entity to database
	 * 
	 * @param updateEntity
	 * @param id
	 * @throws Exception
	 */
	public <S extends IDto<E>> void update(S updateEntity, ID id) throws Exception;

	/**
	 * remove entity in database
	 * 
	 * @param deleteEntity
	 * @param id
	 * @throws Exception
	 */
	public void delete(E deleteEntity, ID id) throws Exception;

	/**
	 * insert entity to database
	 * 
	 * @param newEntity
	 * @throws Exception
	 */
	public void create(E newEntity) throws Exception;

	/**
	 * update entity to database
	 * 
	 * @param updateEntity
	 * @param id
	 * @throws Exception
	 */
	public void update(E updateEntity, ID id) throws Exception;

	/**
	 * remove entity in database
	 * 
	 * @param deleteEntity
	 * @param id
	 * @throws Exception
	 */
	public <S extends IDto<E>> void delete(S deleteEntity, ID id) throws Exception;
}
