package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.interfaces.IDto;
import com.example.demo.interfaces.IService;

/**
 * abtract class for service
 * 
 *
 * @param <E> type of entity
 * @param <ID> type of entity id
 */
@Service
@Transactional(rollbackFor = Exception.class)
public abstract class AbService<E,ID> implements IService<E, ID> {
	@Autowired
	protected JpaRepository<E, ID> repository; 
	
	/**
	 * get all entity in database
	 * 
	 * @return list entities
	 * @throws Exception 
	 * 		@Message=Empty list entity
	 */
	@Override
	public List<E> findAll() throws Exception {
		List<E> result = repository.findAll();
		if(result.size() < 1) {
			throw new Exception("Empty list Entity");
		}
		return result;
	}
	
	@Override
	public Page<E> findAll(int page, int size) throws Exception {
		Pageable  paging = PageRequest.of(page, size);
		Page<E> result = repository.findAll(paging);
		if(result.isEmpty()) {
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
	public E findById(ID id) throws Exception {
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
	public <S extends IDto<E>> void create(S newEntity) throws Exception {
		E ent = newEntity.toEntity();
		repository.save(ent);
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
	public <S extends IDto<E>> void update(S updateEntity, ID id) throws Exception {
		this.findById(id);
		E ent = updateEntity.toEntity();
		repository.save(ent);
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
	public  <S extends IDto<E>> void delete(S deleteEntity, ID id) throws Exception {
		this.findById(id);
		E ent = deleteEntity.toEntity();
		repository.delete(ent);
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
		this.findById(id);
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
		this.findById(id);
		repository.delete(deleteEntity);
	}
}
