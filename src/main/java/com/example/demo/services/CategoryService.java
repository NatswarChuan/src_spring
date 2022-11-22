package com.example.demo.services;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.entities.Category;
import com.example.demo.interfaces.IDto;

@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryService extends AbService<Category, Long> {
	public void remove(IDto<Category> dto) throws Exception {
		Category ent = dto.toEntity();
		if(ent == null) {
			throw new Exception("Bad request");
		}
		Category entFormDB = this.readById(ent.getId());
		if(ent.getUpdateDate().compareTo(entFormDB.getUpdateDate())!= 0) {
			throw new Exception("Bad request");
		}
		this.delete(entFormDB, entFormDB.getId());
	}
	
	public void update(IDto<Category> dto) throws Exception {
		Category ent = dto.toEntity();
		if(ent == null) {
			throw new Exception("Bad request");
		}
		Category entFormDB = this.readById(ent.getId());
		if(ent.getUpdateDate().compareTo(entFormDB.getUpdateDate())!= 0) {
			throw new Exception("Bad request");
		}
		ent.setUpdateDate(new Date());
		this.update(ent, ent.getId());
	}
}
