package com.proj.blogging2.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class SendOtp {
	
	    public static final String ACCOUNT_SID = "ACf6a18ea980e1dc8d8fe95e14150e433e";
	    public static final String AUTH_TOKEN = "4dd29dbbbcf047b3559f716eead266aa";
	    
	    public long otp;
	    
	    public void sendOtp(String contactNo) {
	    	 
	    	otp = (long) (Math.random()*1000000);
	    	
	    	System.out.println("otp: "+otp );
		    
	    	String body = "Your otp for blogging website is : "+ otp;
	    	
	    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		        Message message = Message.creator(
		                new com.twilio.type.PhoneNumber("+91"+contactNo),
		                new com.twilio.type.PhoneNumber("+19705095803"),
		                body)
		            .create();
	
		        System.out.println(message.getSid());
		  
	    
	    }

}
