package com.example.demo.controllers;

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

import com.example.demo.dto.request.category.CategoryCreateRequestDto;
import com.example.demo.dto.request.category.CategoryRemoveRequestDto;
import com.example.demo.dto.request.category.CategoryUpdateRequestDto;
import com.example.demo.entities.Category;
import com.example.demo.interfaces.IController;
import com.example.demo.services.CategoryService;

@Controller
@RequestMapping("category")
public class CategoryController implements IController<Long>{
	@Autowired
	CategoryService categoryService;
	
	@GetMapping(value = { "/", "" })
	@Override
	public String getAll(Model model) {
		List<Category> result;
		try {
			result = categoryService.readAll();
		} catch (Exception e) {
			result = null;
		}
		model.addAttribute("categoriesList", result);
		return "category/list";
	}

	@GetMapping(value = { "/{id}", "/{id}/" })
	@Override
	public String getById(Long id, Model model) {
		Category result;
		CategoryRemoveRequestDto removeRequest = new CategoryRemoveRequestDto();
		try {
			result = categoryService.readById(id);
			removeRequest.parseDto(result);
		} catch (Exception e) {
			result = null;
		}
		model.addAttribute("removeRequest",removeRequest);
		model.addAttribute("category", result);
		return "category/detail";
	}
	
	@PostMapping(value = { "/remove", "/remove/" })
	public String delete(@Validated @ModelAttribute CategoryRemoveRequestDto removeRequest, Model model) {
		try {
			categoryService.remove(removeRequest);
		} catch (Exception e) {
			model.addAttribute("exception", e.getMessage());
			return getById(removeRequest.getId(), model);
		}
		return "redirect:/category";
	}

	@GetMapping(value = { "/update/{id}", "/update/{id}/" })
	public String edit(@PathVariable Long id, Model model) {
		Category result;
		CategoryUpdateRequestDto updateRequest;
		try {
			result = categoryService.readById(id);
			updateRequest = new CategoryUpdateRequestDto();
			updateRequest.parseDto(result);
		} catch (Exception e) {
			result = null;
			updateRequest = null;
			model.addAttribute("exception", e.getMessage());
		}
		model.addAttribute("updateRequest", updateRequest);
		return "category/edit";
	}

	@PostMapping(value = { "/update/{id}", "/update/{id}/" })
	public String update(@Validated @ModelAttribute CategoryUpdateRequestDto updateRequest, BindingResult result,
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
			categoryService.update(updateRequest);
		} catch (Exception e) {
			model.addAttribute("exception", e.getMessage());
			return this.edit(id, model);
		}

		return "redirect:/category/" + id;
	}
	
	@GetMapping(value = { "/create", "/create/" })
	public String create(Model model) {
		CategoryCreateRequestDto createRequest = new CategoryCreateRequestDto();
		model.addAttribute("createRequest", createRequest);
		return "category/create";
	}

	@PostMapping(value = { "/create", "/create/" })
	public String insert(@Validated @ModelAttribute CategoryCreateRequestDto createRequest, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("errorValid", result.getAllErrors());
			return this.create(model);
		}

		try {
			categoryService.create(createRequest.toEntity());
		} catch (Exception e) {
			model.addAttribute("exception", e.getMessage());
			return this.create(model);
		}

		return "redirect:/category";
	}
}
