package com.talktales.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

	private int userid; 
	@NotBlank(message = "Name cannot be empty.")
	@Size(min=4,message="Name must be between 3 and 50 characters")
	private String name;
	@NotBlank(message = "Email cannot be empty.")
	@Email(message="Invalid email format. Please enter a valid email address")
	private String email;
	@NotBlank(message = "Password cannot be empty.")
	private String password;
	@NotNull
	@NotBlank(message = "About section cannot be empty.")
	@Size(min=10,max=300,message="About section atleast have 10 and 300 characters.")
	private String about;
	
	@JsonIgnore
	public String getPassword(String password) {
		return this.password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password=password;
	}
}
