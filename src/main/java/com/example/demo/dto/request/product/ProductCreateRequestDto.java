package com.example.demo.dto.request.product;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.example.demo.entities.Product;
import com.example.demo.interfaces.IDto;

import lombok.Data;

@Data
public class ProductCreateRequestDto implements IDto<Product> {
	
	@NotEmpty(message = "name not empty")
	@Size(max = 64, message = "name length under 64")
	private String name;

	private Long shopId;
	
	@Override
	public Product toEntity() {
		Product ent = new Product();
		Date now = new Date();
		BeanUtils.copyProperties(this, ent,"id");
		ent.setUpdateDate(now);
		ent.setCreateDate(now);
		return ent;
	}

	@Override
	public void parseDto(Product entity) {
		// TODO Auto-generated method stub
		
	}

}
