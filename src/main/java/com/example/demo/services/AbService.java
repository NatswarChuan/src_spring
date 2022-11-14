package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.interfaces.IService;

/**
 * abtract class for service
 * 
 * @author natsw
 *
 * @param <E> type of entity
 * @param <ID> type of entity id
 */
@Service
@Transactional(rollbackFor = Exception.class)
public abstract class AbService<E,ID> implements IService<E, ID> {
	@Autowired
	JpaRepository<E, ID> repository; 
	
	/**
	 * get all entity in database
	 * 
	 * @return list entities
	 * @throws Exception 
	 * 		@Message=Empty list entity
	 */
	@Override
	public List<E> readAll() throws Exception {
		List<E> result = repository.findAll();
		if(result.size() < 1) {
			throw new Exception("Empty list Entity");
		}
		return result;
	}

	/**
	 * get entity by id
	 * 
	 * @param id
	 * @return entity
	 * @throws Exception 
	 * 		@Message=Entity not found with id
	 */
	@Override
	public E readById(ID id) throws Exception {
		Optional<E> result = repository.findById(id);
		if(result.isEmpty()) {
			throw new Exception("Entity not found with id= "+id);
		}
		return result.get();
	}

	/**
	 * insert entity to database
	 * 
	 * @param newEntity
	 * @throws Exception
	 * 		@Message=Entity not found with id
	 */
	@Override
	public void create(E newEntity) throws Exception {
		repository.save(newEntity);
	}

	/**
	 * update entity to database
	 * 
	 * @param updateEntity
	 * @param id
	 * @throws Exception
	 *  	@Message=Entity not found with id
	 */
	@Override
	public void update(E updateEntity, ID id) throws Exception {
		this.readById(id);
		repository.save(updateEntity);
	}

	/**
	 * remove entity in database
	 * 
	 * @param deleteEntity
	 * @param id
	 * @throws Exception
	 *  	@Message=Entity not found with id
	 */
	@Override
	public void delete(E deleteEntity, ID id) throws Exception {
		this.readById(id);
		repository.delete(deleteEntity);
	}

}
