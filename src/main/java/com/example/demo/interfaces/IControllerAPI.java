package com.example.demo.interfaces;

import java.util.List;

public interface IControllerAPI<E,ID> {
	E getById(ID id);
	List<E> getAll();
}
