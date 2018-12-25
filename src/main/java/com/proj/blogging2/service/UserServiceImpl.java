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
		user.setLastName(registration.getLastName());
		user.setGender(registration.getGender());
		user.setUserName(registration.getUserName());
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		user.setEmail(registration.getEmail());
		user.setContactNo(registration.getContactNo());
		
		return userRepository.save(user);
		
	}
	/*
	
	  private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
	        return roles.stream()
	                .map(role -> new SimpleGrantedAuthority(role.getName()))
	                .collect(Collectors.toList());
	    }*/
	  
	  

}
