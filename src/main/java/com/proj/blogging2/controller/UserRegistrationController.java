/*
 When the form is submitted it’s automatically validated and errors
  are available in the BindingResult
*/


package com.proj.blogging2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	@Autowired
	private UserService userService;
	
	@ModelAttribute("user")
	private UserRegistrationDto userRegistrationDto() {
		System.out.println("inside user registration dto\n \n");
		
		return new UserRegistrationDto();
	}
	
	@GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto, 
            BindingResult result) {
		
		System.out.println("inside registerUserAccount");
		User exist = userService.findByEmail(userDto.getEmail());
		if (exist != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
		
		if (result.hasErrors()){
	          return "registration";
        }

		System.out.println("errorless now going to save method in userService ################");
	     userService.save(userDto);
	     return "redirect:/registration?success";
	    
		
	}
	
	
}
