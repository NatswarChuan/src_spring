package com.example.demo.interfaces;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface IController {
	void error(BindingResult result,Model model,String nameError,List<String> errorList);
}
