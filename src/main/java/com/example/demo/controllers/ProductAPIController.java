package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Product;
import com.example.demo.services.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/product")
@AllArgsConstructor
public class ProductAPIController {
	@Autowired
	ProductService productService;

	@GetMapping(value = { "/{id}", "/{id}/" })
	public List<Product> getAll(@PathVariable Long id, Model model) {
		List<Product> result = new ArrayList<>();
		try {
			result = productService.test(id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
}
