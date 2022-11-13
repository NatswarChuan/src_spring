package com.example.demo.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.MstStudent;

import lombok.Data;

@Data
public class StudentsOfClassResponse {
	
	private List<Long> studentsListId;
	
	public StudentsOfClassResponse() {
		this.studentsListId = new ArrayList<>();
	}
	
	public StudentsOfClassResponse(List<MstStudent> studentsList) {
		List<Long> newDataList = new ArrayList<>();
		for (MstStudent studentParam : studentsList) {
			newDataList.add(studentParam.getStudentId());
		}
		this.studentsListId = newDataList;
	}
}
