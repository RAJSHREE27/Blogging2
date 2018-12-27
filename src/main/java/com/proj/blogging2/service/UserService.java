package com.proj.blogging2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.proj.blogging2.dto.UserRegistrationDto;
import com.proj.blogging2.model.User;
import com.proj.blogging2.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	  public User findByUserName(String userName){
		    return userRepository.findByUserName(userName);
	    }

	
	public User save(UserRegistrationDto registration) {
		
		
		User user = new User();
		user.setFirstName(registration.getFirstName());
		user.setLastName(registration.getLastName());
		user.setGender(registration.getGender());
		user.setUserName(registration.getUserName());
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		user.setEmail(registration.getEmail());
		user.setContactNo(registration.getContactNo());
		
		return userRepository.save(user);
		
	}
	
	  

}
