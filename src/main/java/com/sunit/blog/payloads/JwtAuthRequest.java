package com.sunit.blog.payloads;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data

@ToString
public class JwtAuthRequest {	
	private String username;
	private String password;
	public JwtAuthRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
