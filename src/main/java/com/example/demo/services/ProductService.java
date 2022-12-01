package com.example.demo.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.request.category.CategoriesOfProductCreateRequestDto;
import com.example.demo.dto.request.category.CategoriesOfProductUpdateRequestDto;
import com.example.demo.dto.request.product.ProductCreateRequestDto;
import com.example.demo.dto.request.product.ProductRemoveRequestDto;
import com.example.demo.dto.request.product.ProductUpdateRequest;
import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import com.example.demo.entities.Shop;
import com.example.demo.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductService extends AbService<Product, Long> {
	@Autowired
	ProductRepository productRepository;

	@Autowired
	ShopService shopService;
	
	@Autowired
	CategoryService categoryService;

	public void createProduct(ProductCreateRequestDto createRequest) throws Exception {
		Shop shop = shopService.readById(createRequest.getShopId());
		Product product = createRequest.toEntity();
		product.setShop(shop);
		this.create(product);
	}

	public void removeProduct(ProductRemoveRequestDto productRemoveRequest) throws Exception {
		Product product = productRemoveRequest.toEntity();
		if (product == null) {
			throw new Exception("Bad request");
		}
		Product entProduct = this.readById(productRemoveRequest.getId());
		if (product.getUpdateDate().compareTo(entProduct.getUpdateDate()) != 0) {
			throw new Exception("Bad request");
		}
		this.delete(entProduct, entProduct.getId());
	}

	public void updateProduct(ProductUpdateRequest productUpdateRequest,CategoriesOfProductUpdateRequestDto categoriesOfProductRequest) throws Exception {
		Product product = productUpdateRequest.toEntity();
		List<Category> categoriesList = new ArrayList<>();
		if (product == null) {
			throw new Exception("Bad request");
		}
		Product entProduct = this.readById(productUpdateRequest.getId());
		if (product.getUpdateDate().compareTo(entProduct.getUpdateDate()) != 0) {
			throw new Exception("Bad request");
		}
		Shop shop = shopService.readById(productUpdateRequest.getShopId());
		product.setShop(shop);
		if(categoriesOfProductRequest.getListId().size() > 0) {
			Category category;
			for (Long id : categoriesOfProductRequest.getListId()) {
				category = categoryService.readById(id);
				categoriesList.add(category);
			}
		}
		if(product.getCategories() == null) {
			product.setCategories(categoriesList);
		}else {
			product.getCategories().clear();
			categoriesList.forEach(i->{
				product.getCategories().add(i);
			});
		}
		
		product.setUpdateDate(new Date());
		this.update(product, product.getId());
	}

	public Page<Product> getAllPerPage(Integer page, Integer size) throws Exception {
		Pageable paging = PageRequest.of(page, size);
		return productRepository.findAll(paging);
	}

	public void create(ProductCreateRequestDto createRequest,
			CategoriesOfProductCreateRequestDto categoriesOfProductRequest) throws Exception {
		List<Category> categoriesList = new ArrayList<>();
		Product product = createRequest.toEntity();
		Shop shop = shopService.readById(createRequest.getShopId());
	
		product.setShop(shop);
		if(categoriesOfProductRequest.getListId().size() > 0) {
			Category category;
			for (Long id : categoriesOfProductRequest.getListId()) {
				category = categoryService.readById(id);
				categoriesList.add(category);
			}
		}
		
		product.setCategories(categoriesList);
		
		this.create(product);
	}
	
	public List<Product> test(long id){
		return productRepository.getProduct(id);
	}
}
