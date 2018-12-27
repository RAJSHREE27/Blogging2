package com.proj.blogging2.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class UserLoginDto {
	
	@NotEmpty
	private String userName;
	
	@NotEmpty
	private String password;
	
}
