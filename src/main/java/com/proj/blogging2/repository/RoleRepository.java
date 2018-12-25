package com.proj.blogging2.repository;

import org.springframework.data.repository.CrudRepository;

import com.proj.blogging2.model.Role;

public interface RoleRepository extends CrudRepository<Role, String>{
	
	Role findByRoleId(String roleId);
	Role findByRoleName(String roleName);
	
}
