package com.example.demo.interfaces;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * interface for controller
 * 
 * @author natsw
 *
 * @param <ID> type of entity id
 */
public interface IController<ID> {
	
	/**
	 * get all entity in database
	 * 
	 * @param model
	 * @return view or url
	 */
	public String getAll(Model model);
	
	/**
	 * get entity by id entity in databse
	 * 
	 * @param id 
	 * @param model
	 * @return view or url
	 */
	public String getById(@PathVariable  ID id,Model model);
}