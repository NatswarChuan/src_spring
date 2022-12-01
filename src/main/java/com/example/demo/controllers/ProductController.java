package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.request.category.CategoriesOfProductCreateRequestDto;
import com.example.demo.dto.request.category.CategoriesOfProductUpdateRequestDto;
import com.example.demo.dto.request.product.ProductCreateRequestDto;
import com.example.demo.dto.request.product.ProductRemoveRequestDto;
import com.example.demo.dto.request.product.ProductUpdateRequest;
import com.example.demo.entities.Category;
import com.example.demo.entities.Product;
import com.example.demo.entities.Shop;
import com.example.demo.interfaces.IController;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;
import com.example.demo.services.ShopService;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("product")
@AllArgsConstructor
public class ProductController implements IController<Long> {
	private static int SIZE_PAGE = 3;

	@Autowired
	ShopService shopService;

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@GetMapping(value = { "/", "" })
	public String getAll(@RequestParam(defaultValue = "1") int page, Model model) {
		List<Product> result;
		Page<Product> listPerPage;
		List<Integer> pageNumbers = new ArrayList<>();
		try {
			listPerPage = productService.readAll(page - 1, SIZE_PAGE);
			result = listPerPage.toList();
			pageNumbers = IntStream.rangeClosed(1, listPerPage.getTotalPages()).boxed().collect(Collectors.toList());
		} catch (Exception e) {
			result = null;
			listPerPage = null;
		}
		model.addAttribute("productList", result);
		model.addAttribute("listPerPage", listPerPage);
		model.addAttribute("pageNumbers", pageNumbers);
		model.addAttribute("page", page);
		return "product/list";
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@Override
	public String getById(@PathVariable Long id, Model model) {
		Product result;
		ProductRemoveRequestDto removeRequest = new ProductRemoveRequestDto();
		try {
			result = productService.readById(id);
			removeRequest.parseDto(result);
		} catch (Exception e) {
			result = null;
		}
		model.addAttribute("product", result);
		model.addAttribute("removeRequest", removeRequest);
		return "product/detail";
	}

	@Override
	public String getAll(Model model) {
		// TODO Auto-generated method stub
		return null;
	}

	@GetMapping(value = { "/create", "/create/" })
	public String create(Model model) {
		ProductCreateRequestDto createRequest = new ProductCreateRequestDto();
		CategoriesOfProductCreateRequestDto categoriesOfProductRequest = new CategoriesOfProductCreateRequestDto();
		List<Category> categoriesRequest = new ArrayList<>();
		List<Shop> shopsRequest = new ArrayList<>();
		try {
			categoriesRequest = categoryService.readAll();
			shopsRequest = shopService.readAll();
		} catch (Exception e) {
			model.addAttribute("exception", e.getMessage());
		}

		model.addAttribute("categoriesOfProductRequest", categoriesOfProductRequest);
		model.addAttribute("categoriesRequest", categoriesRequest);
		model.addAttribute("shopsRequest", shopsRequest);
		model.addAttribute("createRequest", createRequest);
		return "product/create";
	}

	@PostMapping(value = { "/create", "/create/" })
	public String insert(@Validated @ModelAttribute ProductCreateRequestDto createRequest,
			@Validated @ModelAttribute CategoriesOfProductCreateRequestDto categoriesOfProductRequest,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("errorValid", result.getAllErrors());
			return this.create(model);
		}

		try {
			productService.create(createRequest, categoriesOfProductRequest);
		} catch (Exception e) {
			model.addAttribute("exception", e.getMessage());
			return this.create(model);
		}

		return "redirect:/product/";
	}

	@GetMapping(value = { "/update/{id}", "/update/{id}/" })
	public String edit(@PathVariable Long id, Model model) {
		Product result;
		ProductUpdateRequest updateRequest;
		CategoriesOfProductUpdateRequestDto categoriesOfProductRequest = new CategoriesOfProductUpdateRequestDto();
		List<Category> categoriesRequest = new ArrayList<>();
		List<Shop> shopsRequest = new ArrayList<>();
		try {
			result = productService.readById(id);
			updateRequest = new ProductUpdateRequest();
			updateRequest.parseDto(result);
			categoriesOfProductRequest.parseDto(result.getCategories());
			categoriesRequest = categoryService.readAll();
			shopsRequest = shopService.readAll();
		} catch (Exception e) {
			result = null;
			updateRequest = null;
			model.addAttribute("exception", e.getMessage());
		}
		model.addAttribute("categoriesOfProductRequest", categoriesOfProductRequest);
		model.addAttribute("categoriesRequest", categoriesRequest);
		model.addAttribute("shopsRequest", shopsRequest);
		model.addAttribute("updateRequest", updateRequest);
		return "product/edit";
	}

	@PostMapping(value = { "/update/{id}", "/update/{id}/" })
	public String update(@Validated @ModelAttribute ProductUpdateRequest updateRequest,
			@Validated @ModelAttribute CategoriesOfProductUpdateRequestDto categoriesOfProductRequest,
			BindingResult result, Model model) {
		Long id = Long.MIN_VALUE;
		if (updateRequest.getId() != null) {
			id = updateRequest.getId();
		}

		if (result.hasErrors()) {
			model.addAttribute("errorValid", result.getAllErrors());
			return this.edit(id, model);
		}

		try {
			productService.updateProduct(updateRequest,categoriesOfProductRequest);
		} catch (Exception e) {
			model.addAttribute("exception", e.getMessage());
			return this.edit(id, model);
		}

		return "redirect:/product/" + id;
	}
}
