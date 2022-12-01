package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	@Query(value="SELECT * FROM users WHERE name = ?1 LIMIT 0,1", nativeQuery = true)
	public User readByName(String name);
}
