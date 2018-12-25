package com.proj.blogging2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.proj.blogging2.dto.UserRegistrationDto;
import com.proj.blogging2.model.User;
import com.proj.blogging2.repository.UserRepository;

@Repository
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	  public User findByEmail(String email){
		  System.out.println("inside findByEmail in user serviceImpl");
	        return userRepository.findByEmail(email);
	    }

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid Username or password");
			
		}
		 return new org.springframework.security.core.userdetails.User(user.getUserName(),
	                user.getPassword(), null);
		
	}

	@Override
	public User save(UserRegistrationDto registration) {
		
		System.out.println("inside save methopd of user-service");
		User user = new User();
		user.setFirstName(registration.getFirstName());
		System.out.println("1@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		user.setLastName(registration.getLastName());
		System.out.println("2$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4");
		user.setGender(registration.getGender());
		System.out.println("3$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4");
		
		user.setUserName(registration.getUserName());
		System.out.println("4$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4");
		
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		System.out.println("5$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4");
		
		user.setEmail(registration.getEmail());
		System.out.println("6$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4");
		
		user.setContactNo(registration.getContactNo());
		System.out.println("7$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4");
		
		
		return userRepository.save(user);
		
	}
	/*
	
	  private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
	        return roles.stream()
	                .map(role -> new SimpleGrantedAuthority(role.getName()))
	                .collect(Collectors.toList());
	    }*/
	  
	  

}
