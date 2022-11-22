package com.example.demo.dto.request.category;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.example.demo.entities.Category;
import com.example.demo.interfaces.IDto;

import lombok.Data;

@Data
public class CategoryCreateRequestDto implements IDto<Category>{
	@NotEmpty(message = "name not empty")
	@Size(max = 64, message = "name length under 64")
	private String name;
	
	@Override
	public Category toEntity() {
		Category result = new Category();
		Date now = new Date();
		BeanUtils.copyProperties(this, result, "id");
		result.setUpdateDate(now);
		result.setCreateDate(now);
		return result;
	}

	@Override
	public void parseDto(Category entity) {
	}

}
