package com.proj.blogging2.api;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.blogging2.model.Role;
import com.proj.blogging2.model.User;
import com.proj.blogging2.repository.RoleRepository;
import com.proj.blogging2.repository.UserRepository;

@RestController
@RequestMapping("/users/api")
public class RegistrationApiController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@PostMapping("/registration")
	public void registration(@RequestBody User user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		Set<Role> roles=new HashSet<Role>();
		roles.add(roleRepository.findByRoleId("1"));
		user.setRoles(roles);
		userRepository.save(user);
		
	}
	
	
}
