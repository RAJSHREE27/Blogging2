package com.proj.blogging2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
 
import com.proj.blogging2.dto.UserLoginDto;
import com.proj.blogging2.model.User;
import com.proj.blogging2.service.UserService;

@RequestMapping("/login")
public final class LoginController {
	
	@Autowired
	private UserService userService;
	
	/*
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;	*/
	
	@ModelAttribute("user_login")
	private UserLoginDto userLoginDto() {
		
		System.out.println("coming to user login dto ##################");
		return new UserLoginDto();
		
	}
	
	@GetMapping
	public String showLoginForm() {
		return "login";
	}
	
	@PostMapping
	public String loginUserAccount(@ModelAttribute("user_login") @Valid UserLoginDto userLoginDto,
			  BindingResult result) {
		
		
		System.out.println("coming to verify method !@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("entered password : "+userLoginDto.getUserName());
		
		User userForLogin = userService.findByUserName(userLoginDto.getUserName());
		
		
		if(userForLogin==null) {
			result.rejectValue("userName",null, "wrong credentials");
		}
		
		System.out.print("stored password : "+userForLogin.getPassword());
		
		if(BCrypt.checkpw(userLoginDto.getPassword(), userForLogin.getPassword())) {
			return "redirect:/login?success";
			   
		}else {
			return "login";
		}
		
		/*
		if(result.hasErrors()) {
			return "login";
		}
		return "redirect:/login?success";
	    */
			
	}
	
}
