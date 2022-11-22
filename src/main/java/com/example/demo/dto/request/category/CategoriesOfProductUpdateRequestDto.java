package com.example.demo.dto.request.category;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entities.Category;
import com.example.demo.interfaces.IDto;

import lombok.Data;

@Data
public class CategoriesOfProductUpdateRequestDto implements IDto<Category> {

	private List<Long> listId;
	
	@Override
	public Category toEntity() {
		return null;
	}

	@Override
	public void parseDto(Category entity) {
		
	}

	public void parseDto(List<Category> listEntities) {
		if(listId == null) {
			listId = new ArrayList<>();
		}
		listEntities.forEach(i ->{
			listId.add(i.getId());
		});
	}

	
}
