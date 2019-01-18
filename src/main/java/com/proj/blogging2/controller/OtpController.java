package com.proj.blogging2.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.proj.blogging2.model.User;
import com.proj.blogging2.utils.SendOtp;

@Controller
@RequestMapping("/otp")
public class OtpController {
	
	private Optional<User> userForOtp;
	
	public OtpController(Optional<User> exist) {
		userForOtp = exist;
	}

	@RequestMapping(value= "/recieveotp", method = RequestMethod.GET )
	public String recieveOtp(Model model) {
		
		SendOtp otpSend = new SendOtp();
		
		otpSend.sendOtp(Long.toString(userForOtp.get().getContactNo()));
		model.addAttribute("otp",otpSend.otp);
		
		return "/OtpVerification";
		//frontend
		
	}
	
	/*@RequestMapping(value="/recieveotp", method = RequestMethod.POST)
	public String createUser( BindingResult bindingResult) {
		
		
		
	}*/
	
	
}
