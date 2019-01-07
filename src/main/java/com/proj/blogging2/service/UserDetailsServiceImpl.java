package com.proj.blogging2.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proj.blogging2.model.User;
import com.proj.blogging2.model.UserDetailsImpl;
import com.proj.blogging2.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Optional<User> user = userRepository.findByUserName(userName);
		if(user.isPresent()) {
			return new UserDetailsImpl(user);
		
		}else {
			
			throw new UsernameNotFoundException(userName);
		}
	}
	
	
}
