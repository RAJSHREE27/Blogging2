package com.proj.blogging2.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id; 
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


import lombok.Data;

@Data
@Entity
@Table(name="user_info")
public class User {
	
	@Id
	@Column(name="user_name" , nullable=false , unique = true)
	private String userName;
	//generated value will be for post model class
	
	
	@Column(name="password" , nullable=false)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@Column(name="firstname" , nullable=false)
	@NotEmpty(message = "Please provide your first name")
	private String firstName;
	
	@Column(name="lastname" , nullable=false)
	@NotEmpty(message = "Please provide your last name")
	private String lastName;
	
	@Column(name="gender" , nullable=false)
	private String gender;
	
	@Column(name="email" , nullable=false , unique = true)
	@NotEmpty(message = "Please provide your email-id")
	private String email;
	
	@Column(name="contact_no" , nullable=false , unique = true)
	@NotNull(message = "Please provide your contact number")
	private long contactNo;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="UserRole", joinColumns= @JoinColumn(name="user_name") , inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles;
	
	@OneToMany(mappedBy = "user_info")
	private Collection<Post> posts;

	
	
}
