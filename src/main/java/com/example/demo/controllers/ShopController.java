package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.request.category.CategoriesOfProductCreateRequestDto;
import com.example.demo.dto.request.category.CategoriesOfProductUpdateRequestDto;
import com.example.demo.dto.request.product.ProductCreateRequestDto;
import com.example.demo.dto.request.product.ProductRemoveRequestDto;
import com.example.demo.dto.request.product.ProductUpdateRequest;
import com.example.demo.dto.request.shop.ShopCreateRequestDto;
import com.example.demo.dto.request.shop.ShopRemoveRequestDto;
import com.example.demo.dto.request.shop.ShopUpdateRequestDto;
import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import com.example.demo.entities.Shop;
import com.example.demo.interfaces.IController;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;
import com.example.demo.services.ShopService;

@Controller
@RequestMapping("shop")
public class ShopController implements IController<Long> {
	@Autowired
	ShopService shopService;

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@GetMapping(value = { "/", "" })
	@Override
	public String getAll(Model model) {
		List<Shop> result;
		try {
			result = shopService.readAll().stream().sorted(Comparator.comparingLong(Shop::getId)).toList();
		} catch (Exception e) {
			result = null;
		}
		model.addAttribute("shopList", result);
		return "shop/list";
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@Override
	public String getById(@PathVariable Long id, Model model) {
		Shop result;
		ShopRemoveRequestDto removeRequest = new ShopRemoveRequestDto();
		ProductRemoveRequestDto removeProductRequest = new ProductRemoveRequestDto();
		try {
			result = shopService.readById(id);
			removeRequest.parseDto(result);
		} catch (Exception e) {
			result = null;
		}
		model.addAttribute("shop", result);
		model.addAttribute("removeRequest", removeRequest);
		model.addAttribute("removeProductRequest", removeProductRequest);
		return "shop/detail";
	}

	@PostMapping(value = { "/remove", "/remove/" })
	public String delete(@Validated @ModelAttribute ShopRemoveRequestDto removeRequest, Model model) {
		try {
			shopService.remove(removeRequest);
		} catch (Exception e) {
			model.addAttribute("exception", e.getMessage());
			return getById(removeRequest.getId(), model);
		}
		return "redirect:/shop";
	}

	@GetMapping(value = { "/update/{id}", "/update/{id}/" })
	public String edit(@PathVariable Long id, Model model) {
		Shop result;
		ShopUpdateRequestDto updateRequest;
		try {
			result = shopService.readById(id);
			updateRequest = new ShopUpdateRequestDto();
			updateRequest.parseDto(result);
		} catch (Exception e) {
			result = null;
			updateRequest = null;
			model.addAttribute("exception", e.getMessage());
		}
		model.addAttribute("updateRequest", updateRequest);
		return "shop/edit";
	}

	@PostMapping(value = { "/update/{id}", "/update/{id}/" })
	public String update(@Validated @ModelAttribute ShopUpdateRequestDto updateRequest, BindingResult result,
			Model model) {
		Long id = Long.MIN_VALUE;
		if (updateRequest.getId() != null) {
			id = updateRequest.getId();
		}

		if (result.hasErrors()) {
			model.addAttribute("errorValid", result.getAllErrors());
			return this.edit(id, model);
		}

		try {
			shopService.update(updateRequest);
		} catch (Exception e) {
			model.addAttribute("exception", e.getMessage());
			return this.edit(id, model);
		}

		return "redirect:/shop/" + id;
	}

	@GetMapping(value = { "/create", "/create/" })
	public String create(Model model) {
		ShopCreateRequestDto createRequest = new ShopCreateRequestDto();
		model.addAttribute("createRequest", createRequest);
		return "shop/create";
	}

	@PostMapping(value = { "/create", "/create/" })
	public String insert(@Validated @ModelAttribute ShopCreateRequestDto createRequest, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("errorValid", result.getAllErrors());
			return this.create(model);
		}

		try {
			shopService.create(createRequest.toEntity());
		} catch (Exception e) {
			model.addAttribute("exception", e.getMessage());
			return this.create(model);
		}

		return "redirect:/shop/";
	}

	@GetMapping(value = { "/create/product/{id}", "/create/product/{id}/" })
	public String createProduct(@PathVariable Long id, Model model) {
		ProductCreateRequestDto createRequest = new ProductCreateRequestDto();
		CategoriesOfProductCreateRequestDto categoriesOfProductRequest = new CategoriesOfProductCreateRequestDto();
		List<Category> categoriesRequest = new ArrayList<>();
		createRequest.setShopId(id);
		Shop shop;
		try {
			shop = shopService.readById(id);
			categoriesRequest = categoryService.readAll();
		} catch (Exception e) {
			shop = null;
			model.addAttribute("exception", e.getMessage());
		}
		model.addAttribute("shop", shop);
		model.addAttribute("categoriesOfProductRequest", categoriesOfProductRequest);
		model.addAttribute("categoriesRequest", categoriesRequest);
		model.addAttribute("createRequest", createRequest);
		return "product/create";
	}

	@PostMapping(value = { "/create/product/{id}", "/create/product/{id}/" })
	public String insertProduct(@Validated @ModelAttribute ProductCreateRequestDto createRequest,
			@Validated @ModelAttribute CategoriesOfProductCreateRequestDto categoriesOfProductRequest,
			BindingResult result, @PathVariable Long id, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("errorValid", result.getAllErrors());
			return this.createProduct(id, model);
		}
		try {
			productService.create(createRequest, categoriesOfProductRequest);
		} catch (Exception e) {
			model.addAttribute("exception", e.getMessage());
		}
		return "redirect:/shop/" + id;
	}

	@PostMapping(value = { "/remove/product/{id}", "/remove/product/{id}/" })
	public String removeProduct(@PathVariable Long id,
			@Validated @ModelAttribute ProductRemoveRequestDto removeProductRequest, BindingResult result,
			Model model) {
		try {
			productService.removeProduct(removeProductRequest);
		} catch (Exception e) {
			model.addAttribute("exception", e.getMessage());
		}
		return "redirect:/shop/" + id;
	}

	@GetMapping(value = { "/update/product/{shopId}/{productId}", "/update/product/{shopId}/{productId}/" })
	public String editProduct(@PathVariable Long shopId, @PathVariable Long productId, Model model) {
		ProductUpdateRequest updateRequest = new ProductUpdateRequest();
		CategoriesOfProductUpdateRequestDto categoriesOfProductRequest = new CategoriesOfProductUpdateRequestDto();
		
		List<Category> categoriesRequest = new ArrayList<>();
		Shop shop = null;
		try {
			Product product = productService.readById(productId);
			updateRequest.parseDto(product);
			shopService.readById(shopId);
			updateRequest.setShopId(shopId);
			shop = shopService.readById(shopId);
			categoriesOfProductRequest.parseDto(product.getCategories());
			categoriesRequest = categoryService.readAll();
		} catch (Exception e) {
			model.addAttribute("exception", e.getMessage());
		}
		model.addAttribute("shop", shop);
		model.addAttribute("updateRequest", updateRequest);
		model.addAttribute("categoriesOfProductRequest", categoriesOfProductRequest);
		model.addAttribute("categoriesRequest", categoriesRequest);
		return "product/edit";
	}

	@PostMapping(value = { "/update/product/{shopId}/{productId}", "/update/product/{shopId}/{productId}/" })
	public String updateProduct(@PathVariable Long shopId, @PathVariable Long productId,
			@Validated @ModelAttribute ProductUpdateRequest updateRequest,@Validated @ModelAttribute CategoriesOfProductUpdateRequestDto categoriesOfProductRequest, BindingResult result, Model model) {
		try {
			productService.updateProduct(updateRequest,categoriesOfProductRequest);
		} catch (Exception e) {
			model.addAttribute("exception", e.getMessage());
			return this.editProduct(shopId, productId, model);
		}
		return "redirect:/shop/" + shopId;
	}
}
