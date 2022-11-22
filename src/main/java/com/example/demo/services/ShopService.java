package com.example.demo.services;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.Shop;
import com.example.demo.interfaces.IDto;
import com.example.demo.repositories.ShopRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class ShopService extends AbService<Shop, Long> {
	@Autowired
	ShopRepository repository;
	
	public void remove(IDto<Shop> dto) throws Exception {
		Shop ent = dto.toEntity();
		if(ent == null) {
			throw new Exception("Bad request");
		}
		Shop entFormDB = this.readById(ent.getId());
		if(ent.getUpdateDate().compareTo(entFormDB.getUpdateDate())!= 0) {
			throw new Exception("Bad request");
		}
		this.delete(entFormDB, entFormDB.getId());
	}
	
	public void update(IDto<Shop> dto) throws Exception {
		Shop ent = dto.toEntity();
		if(ent == null) {
			throw new Exception("Bad request");
		}
		Shop entFormDB = this.readById(ent.getId());
		if(ent.getUpdateDate().compareTo(entFormDB.getUpdateDate())!= 0) {
			throw new Exception("Bad request");
		}
		ent.setUpdateDate(new Date());
		this.update(ent, ent.getId());
	}
}
