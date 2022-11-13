package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MstStudent;

public interface MstStudentRepository extends JpaRepository<MstStudent, Long> {

}
