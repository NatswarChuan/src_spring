package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.dto.UserDto;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;

public class UserService extends AbService<User, Long> implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		User user1 = userRepo.readByName(name);
		return new UserDto(user1);
	}

}
