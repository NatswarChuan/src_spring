package com.example.demo.dto.request.product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.example.demo.entities.Product;
import com.example.demo.interfaces.IDto;

import lombok.Data;

@Data
public class ProductRemoveRequestDto implements IDto<Product> {
	private Long id;

	private String updateDate;

	@Override
	public Product toEntity() {
		Product result = null;
		if (id != null) {
			result = new Product();
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
	public void parseDto(Product entity) {
		BeanUtils.copyProperties(entity, this);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formattedDate = formatter.format(entity.getUpdateDate());
		this.updateDate = formattedDate;
	}
}
