package com.example.demo.dto.param;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;

import com.example.demo.entity.MstStudent;

import lombok.Data;

@Data
public class StudentListParam {

	/**
	 * コンストラクタ
	 */
	public StudentListParam() {
		dataList = new ArrayList<StudentParam>();
	}
	
	public void setDataList(List<MstStudent> dataList) {
		for (MstStudent mstStudent : dataList) {
			StudentParam itemDataList = new StudentParam();
			BeanUtils.copyProperties(mstStudent, itemDataList);
			this.dataList.add(itemDataList);
		}
	}
	
	/**
	 * 学生情報リスト
	 */
	@Valid
	private List<StudentParam> dataList; 
}
