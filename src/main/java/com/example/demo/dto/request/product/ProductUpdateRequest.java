package com.example.demo.dto.request.product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.example.demo.entities.Product;
import com.example.demo.interfaces.IDto;

import lombok.Data;

@Data
public class ProductUpdateRequest implements IDto<Product> {

	@NotNull
	private Long id;
	
	@NotEmpty(message = "name not empty")
	@Size(max = 64, message = "name length under 64")
	private String name;
	
	private String updateDate;
	
	private Long shopId;
	
	@Override
	public Product toEntity() {
		Product result = null;
		if(id != null) {
			result = new Product();
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
	public void parseDto(Product entity) {
		BeanUtils.copyProperties(entity, this);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = formatter.format(entity.getUpdateDate());
		this.shopId = entity.getShop().getId();
		this.updateDate = formattedDate;
	}

}
