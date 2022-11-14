package com.example.demo.interfaces;

import java.util.List;

/**
 * interface for service
 * 
 * @author natsw
 *
 * @param <E> type of entity
 * @param <ID> type of entity id
 */
public interface IService<E,ID> {
	/**
	 * get all entity in database
	 * 
	 * @return list entities
	 * @throws Exception
	 */
	public List<E> readAll() throws Exception;
	
	/**
	 * get entity by id
	 * 
	 * @param id
	 * @return entity
	 * @throws Exception
	 */
	public E readById(ID id) throws Exception;
	
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
	public void update(E updateEntity,ID id) throws Exception;

	/**
	 * remove entity in database
	 * 
	 * @param deleteEntity
	 * @param id
	 * @throws Exception
	 */
	public void delete(E deleteEntity,ID id) throws Exception;
}
