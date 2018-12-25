package com.proj.blogging2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.Data;

@Data
@Entity
@Table(name="role")
public class Role {
	
	@Id
	@Column(name="role_id" )
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String roleId;
	
	
	@Column(name="role_name",  nullable=false , unique=true)
	private String roleName;
}
