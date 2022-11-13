package com.example.demo.interfaces;

import java.util.List;

public interface IService<E,ID> {
	void create(E newEntity);
	void update(E entityUpdate) throws Exception;
	void remove(E entityDelete) throws Exception;
	E getById(ID id) throws Exception;
	List<E> getAll();
}
