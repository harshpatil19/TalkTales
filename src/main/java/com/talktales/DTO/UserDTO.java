package com.talktales.DTO;

import jakarta.validation.constraints.Email;
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
	@NotNull
	@Size(min=4,message="Username must be greater than 4 Characters")
	private String name;
	@Email(message="Invalid Email!!!")
	@Pattern(regexp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
	private String email;
	@NotNull
	@NotEmpty
	@Size(min=3,max=10, message="Password must be between 3-10 characters!")
	private String password;
	@NotEmpty
	private String about;
}
