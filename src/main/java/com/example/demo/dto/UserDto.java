package com.example.demo.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entities.User;
import com.example.demo.interfaces.IDto;

import lombok.Data;

@Data
public class UserDto implements UserDetails, IDto<User>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user;
	public UserDto(User user) {
		this.user = user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		final List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public User toEntity() {
		if(this.user != null) {
			return this.user;	
		}
		return null;
	}

	@Override
	public void parseDto(User entity) {
		this.user = entity;
	}

}
