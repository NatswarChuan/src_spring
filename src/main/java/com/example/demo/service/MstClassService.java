package com.example.demo.service;



import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.response.StudentsOfClassResponse;
import com.example.demo.entity.MstClass;
import com.example.demo.entity.MstStudent;
import com.example.demo.interfaces.IService;
import com.example.demo.repository.MstClassRepository;
import com.example.demo.repository.MstStudentRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class MstClassService implements IService<MstClass, Long> {

	@Autowired
	private MstClassRepository mstClassRepository;

	@Autowired
	private MstStudentService mstStudentService;

	@Override
	public void create(MstClass newEntity) {
		mstClassRepository.save(newEntity);
	}

	@Override
	public void update(MstClass entityUpdate) throws Exception {
		Date now = new Date();
		this.getById(entityUpdate.getClassId());
		entityUpdate.setUpdatedDate(now);
		mstClassRepository.save(entityUpdate);
	}

	@Override
	public void remove(MstClass entityDelete) throws Exception {
		this.getById(entityDelete.getClassId());
		mstClassRepository.delete(entityDelete);
	}

	@Override
	public MstClass getById(Long id) throws Exception {
		Optional<MstClass> tempResult = mstClassRepository.findById(id); 
		if(tempResult.isEmpty()) {
			throw new Exception("Class not Found");
		}
		return tempResult.get();
	}

	@Override
	public List<MstClass> getAll() {
		return mstClassRepository.findAll();
	}

	public void updateStudentsOfClass(MstClass entityUpdate,StudentsOfClassResponse studentsOfClassResponse) throws Exception {
		MstClass tempClass = this.getById(entityUpdate.getClassId());
		tempClass.getMstStudentList().clear();
		for (Long studentId : studentsOfClassResponse.getStudentsListId()) {
			tempClass.getMstStudentList().add(mstStudentService.getById(studentId));
		}
	}
}
