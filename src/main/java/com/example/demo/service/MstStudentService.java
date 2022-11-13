package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.param.StudentListParam;
import com.example.demo.entity.MstStudent;
import com.example.demo.interfaces.IService;
import com.example.demo.repository.MstStudentRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class MstStudentService implements IService<MstStudent, Long>{

	@Autowired
	private MstStudentRepository mstStudentRepository;

	@Override
	public void create(MstStudent newEntity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(MstStudent entityUpdate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(MstStudent entityDelete) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MstStudent getById(Long id) throws Exception {
		Optional<MstStudent> tempStudent = mstStudentRepository.findById(id);
		if(tempStudent.isEmpty()) {
			throw new Exception("Student not found");
		}
		return tempStudent.get();
	}

	@Override
	public List<MstStudent> getAll() {
		return mstStudentRepository.findAll();
	}

	public StudentListParam searchAllToParam() {
		StudentListParam result = new StudentListParam();
		result.setDataList(this.getAll());
		return result;
	}
}
