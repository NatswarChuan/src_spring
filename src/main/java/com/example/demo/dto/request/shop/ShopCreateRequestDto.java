package com.example.demo.dto.request.shop;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.example.demo.entities.Shop;
import com.example.demo.interfaces.IDto;

import lombok.Data;

@Data
public class ShopCreateRequestDto implements IDto<Shop>{
	@NotEmpty(message = "name not empty")
	@Size(max = 64, message = "name length under 64")
	private String name;
	
	@Override
	public Shop toEntity() {
		Shop result = new Shop();
		Date now = new Date();
		BeanUtils.copyProperties(this, result, "id");
		result.setUpdateDate(now);
		result.setCreateDate(now);
		return result;
	}

	@Override
	public void parseDto(Shop entity) {
	}

}
