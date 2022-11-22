package com.example.demo.dto.request.category;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.example.demo.entities.Category;
import com.example.demo.interfaces.IDto;

import lombok.Data;
@Data
public class CategoryRemoveRequestDto implements IDto<Category> {
	
	private Long id;
	
	private String updateDate;

	@Override
	public Category toEntity(){
		Category result = null;
		if(id != null) {
			result = new Category();
			SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				Date timeUpdate = parser.parse(updateDate);
				result.setUpdateDate(timeUpdate);
				result.setId(id);
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
