package com.proj.blogging2.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.proj.blogging2.model.Role;
import com.proj.blogging2.model.User;

public interface UserRepository  extends CrudRepository<User, String> {
	
	//fetches data from database by applying queries!
	
	User findByUserName(String userName);
	User findByEmail(String email);
	User findByContactNo(long contactNo);
	User findByRoles(Set<Role> roles);
	
}
