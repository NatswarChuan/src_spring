package com.example.demo.interfaces;

/**
 * Interface for data transfer object
 * 
 * @author natsw
 *
 * @param <E> type of entity
 */
public interface IDto<E> {
	/**
	 * parse dto to entity
	 * @return entity
	 */
	public E toEntity();
	
	/**
	 * parse entity to dto
	 * @param entity
	 */
	public void toDto(E entity);
}
