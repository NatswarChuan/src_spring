package com.example.demo.dto.request.shop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.example.demo.entities.Shop;
import com.example.demo.interfaces.IDto;

import lombok.Data;
@Data
public class ShopRemoveRequestDto implements IDto<Shop> {
	
	private Long id;
	
	private String updateDate;

	@Override
	public Shop toEntity(){
		Shop result = null;
		if(id != null) {
			result = new Shop();
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
	public void parseDto(Shop entity) {
		BeanUtils.copyProperties(entity, this);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = formatter.format(entity.getUpdateDate());
		this.updateDate = formattedDate;
	}
}
