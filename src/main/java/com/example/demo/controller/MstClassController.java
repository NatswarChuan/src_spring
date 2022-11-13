package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dto.param.StudentListParam;
import com.example.demo.dto.request.ClassRequest;
import com.example.demo.dto.response.StudentsOfClassResponse;
import com.example.demo.entity.MstClass;
import com.example.demo.interfaces.IController;
import com.example.demo.service.MstClassService;
import com.example.demo.service.MstStudentService;

@Controller
@RequestMapping("/mstClass")
public class MstClassController implements IController {

	final static Map<String, Integer> RADIO_ITEMS = Collections.unmodifiableMap(new LinkedHashMap<String, Integer>() {
		{
			put("有効", 0);
			put("無効", 1);
		}
	});

	@Autowired
	private MstClassService mstClassService;
	@Autowired
	private MstStudentService mstStudentService;

	@GetMapping(value = "/list")
	public String displayList(Model model) {
		List<MstClass> classList = mstClassService.getAll();
		model.addAttribute("mstClasslist", classList);
		return "mstClass/list";
	}

	/**
	 * màn hình thêm
	 * 
	 * @param model
	 * @return file HTML
	 */
	@GetMapping(value = "/add")
	public String displayAdd(Model model) {
		ClassRequest classRequest = new ClassRequest();
		model.addAttribute("classRequest", classRequest);
		return "mstClass/add";
	}

	/**
	 * create class
	 * 
	 * @param classRequest
	 * @param result
	 * @param model
	 * @return view
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createNewClass(@Validated @ModelAttribute ClassRequest classRequest, BindingResult result,
			Model model) {
		try {
			if (result.hasErrors()) {
				List<String> errorList = new ArrayList<>();
				this.error(result, model, "validationError",errorList);
				model.addAttribute("classRequest", classRequest);
				return "mstClass/add";
			}
			Date now = new Date();
			MstClass newClass = classRequest.toEntity();
			newClass.setDataStatus(0);
			newClass.setRegisteredDate(now);
			newClass.setUpdatedDate(now);
			mstClassService.create(newClass);
			return "redirect:/mstClass/list";
		} catch (Exception e) {
			model.addAttribute("validationError", e.getMessage());
			model.addAttribute("classRequest", classRequest);
			return "mstClass/add";
		}
	}

	/**
	 * get class by id
	 * 
	 * @param id
	 * @param model
	 * @return view
	 */
	@GetMapping("/{id}")
	public String displayView(@PathVariable Long id, Model model) {
		model.addAttribute("radioItems", RADIO_ITEMS);
		MstClass data;
		StudentsOfClassResponse studentsOfClassResponse;

		StudentListParam listMstStudent = mstStudentService.searchAllToParam();
		model.addAttribute("listMstStudent", listMstStudent);

		try {
			data = mstClassService.getById(id);
			studentsOfClassResponse = new StudentsOfClassResponse(data.getMstStudentList());
		} catch (Exception e) {
			data = new MstClass();
			data.setClassId(id);
			studentsOfClassResponse = new StudentsOfClassResponse();
			model.addAttribute("validationError", e.getMessage());
		}

		model.addAttribute("studentsOfClassResponse", studentsOfClassResponse);
		model.addAttribute("mstClass", data);
		return "mstClass/edit";
	}

	/**
	 * update class by id
	 * 
	 * @param classRequest
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String updateClass(@Validated @ModelAttribute ClassRequest classRequest,
			@ModelAttribute StudentsOfClassResponse studentsOfClassResponse, BindingResult result, Model model) {
		List<String> errorList = new ArrayList<String>();
		MstClass data = null;
		try {
			if (result.hasErrors()) {
				this.error(result, model, "validationError",errorList);
				long classId = classRequest.getClassId();
				data = mstClassService.getById(classId);
				model.addAttribute("mstClass", data);
				model.addAttribute("radioItems", RADIO_ITEMS);
				return "mstClass/edit";
			}
			data = classRequest.toEntity();
			mstClassService.updateStudentsOfClass(data, studentsOfClassResponse);
			mstClassService.update(data);
			return "redirect:/mstClass/list";
		} catch (Exception e) {
			if(data == null) {
				data = new MstClass();
				data.setClassId(classRequest.getClassId());
			}
			model.addAttribute("mstClass", data);
			model.addAttribute("radioItems", RADIO_ITEMS);
			model.addAttribute("validationError", errorList);
			return "mstClass/edit";
		}
	}

	/**
	 * delete class by id
	 * 
	 * @param classRequest
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteClass(@Validated @ModelAttribute ClassRequest classRequest, BindingResult result, Model model) {
		try {
			mstClassService.remove(classRequest.toEntity());
		} catch (Exception e) {
			model.addAttribute("validationError", e.getMessage());
			return this.displayView(classRequest.getClassId(), model);
		}
		return "redirect:/mstClass/list";
	}

	/**
	 * hàm gửi error
	 * 
	 * @param result
	 * @param model
	 * @param errorList
	 * @param nameError
	 * @return
	 */
	@Override
	public void error(BindingResult result, Model model, String nameError,List<String> errorList) {
		for (ObjectError error : result.getAllErrors()) {
			errorList.add(error.getDefaultMessage());
		}
		model.addAttribute(nameError, errorList);
	}
}