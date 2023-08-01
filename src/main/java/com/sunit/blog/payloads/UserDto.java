package com.sunit.blog.payloads;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

	private int userId;
	@NotEmpty
	@Size(min=4, message="username must be of min 4 char...!")
	private String name;
	@Email(message = "email address is not valid...!")
	private String email;
	@NotEmpty
	@Size(min=3,max=10, message="Password must be of min of 3 and max of 10 chars...!")
	private String pwd;
	@NotEmpty
	private String about;
	
	private List<RoleDto> roles = new ArrayList<>();

}
