package com.example.demo.dto.request.category;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.example.demo.entities.Category;
import com.example.demo.interfaces.IDto;

import lombok.Data;

@Data
public class CategoryUpdateRequestDto implements IDto<Category> {
	
	@NotNull
	private Long id;
	
	@NotEmpty(message = "name not empty")
	@Size(max = 64, message = "name length under 64")
	private String name;
	
	private String updateDate;
	
	@Override
	public Category toEntity() {
		Category result = null;
		if(id != null) {
			result = new Category();
			SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date timeUpdate = parser.parse(updateDate);
				result.setUpdateDate(timeUpdate);
				BeanUtils.copyProperties(this, result);
			} catch (ParseException e) {
				result.setUpdateDate(new Date());
			}	
		}
		return result;
	}

	@Override
	public void parseDto(Category entity) {
		BeanUtils.copyProperties(entity, this);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = formatter.format(entity.getUpdateDate());
		this.updateDate = formattedDate;
	}

}
