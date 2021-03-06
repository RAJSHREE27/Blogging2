/*
 When the form is submitted it’s automatically validated and errors
  are available in the BindingResult
*/


package com.proj.blogging2.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proj.blogging2.dto.UserRegistrationDto;
import com.proj.blogging2.model.User;
import com.proj.blogging2.service.UserService;
import com.proj.blogging2.utils.SendOtp;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	protected AuthenticationManager authenticationManager;
	
	@ModelAttribute("user")
	private UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}
	
	@GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto, 
            BindingResult result) {
		
		Optional<User> exist = userService.findByUserName(userDto.getUserName());
		if (exist.isPresent()){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
		
		if (result.hasErrors()){
	          return "registration";
        }
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println(userDto.getContactNo());
		
		SendOtp otpSend = new SendOtp();
		
		otpSend.sendOtp(Long.toString(userDto.getContactNo()));
		System.out.println(Long.toString(userDto.getContactNo()));
		
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		
		// userService.save(userDto);
		 
	     return "redirect:/home";
	   
	}
	
	public void authenticateUserAndSetSession(User user, HttpServletRequest request) {
		
		String userName = user.getUserName();
		String password = user.getPassword();
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, password);
		
		// generate session if one doesn't exist
		request.getSession();
		token.setDetails(new WebAuthenticationDetails(request));
		
		Authentication authenticatedUser = authenticationManager.authenticate(token);

		SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
			
		
	}
	
	
}
