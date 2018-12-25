/* We use the UserRegistrationDto to validate the user registration form. 
 * This DTO is annotated using Hibernate-Validation annotations which validate 
 * trivial fields on empty and our own custom @FieldMatch annotations which validates
 *  if the password is equal to the confirm 
 * password and the email address field is equal to the confirm email address field.
 */

package com.proj.blogging2.dto;

import javax.validation.constraints.Email;

import com.proj.blogging2.constraint.FieldMatch;
import javax.validation.constraints.NotNull;

import lombok.Data;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
    @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})

@Data
public class UserRegistrationDto {
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private String gender;
	
	@NotNull
	private String userName;
	
	@NotNull
	private String password;
	
	@NotNull
	private String confirmPassword;
	
	
	@Email
	@NotNull
	private String email;
	
	@NotNull
	private String confirmEmail;
	
	@NotNull
	private long contactNo;
	
	
}
