//only used for registration

package com.proj.blogging2.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.proj.blogging2.dto.UserRegistrationDto;
import com.proj.blogging2.model.Role;
import com.proj.blogging2.model.User;
import com.proj.blogging2.repository.RoleRepository;
import com.proj.blogging2.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	  public User findByUserName(String userName){
		    return userRepository.findByUserName(userName);
	    }

	
	public User save(UserRegistrationDto registration) {
		
		Set<Role> roles=new HashSet<Role>();
		roles.add(roleRepository.findByRoleId("1"));
		User user = new User();
		user.setFirstName(registration.getFirstName());
		user.setLastName(registration.getLastName());
		user.setGender(registration.getGender());
		user.setUserName(registration.getUserName());
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		user.setEmail(registration.getEmail());
		user.setContactNo(registration.getContactNo());
		
		user.setRoles(roles);
		return userRepository.save(user);
		
	}
	
	  

}
