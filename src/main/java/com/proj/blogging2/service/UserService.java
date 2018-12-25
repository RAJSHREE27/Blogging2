package com.proj.blogging2.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.proj.blogging2.dto.UserRegistrationDto;
import com.proj.blogging2.model.User;

@Service
public interface UserService extends UserDetailsService {
	
	User findByEmail(String email);
	
	User save(UserRegistrationDto registration);
	
}
