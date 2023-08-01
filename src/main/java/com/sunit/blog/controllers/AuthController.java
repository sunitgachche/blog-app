package com.sunit.blog.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunit.blog.exceptions.ApiException;
import com.sunit.blog.payloads.JwtAuthRequest;
import com.sunit.blog.payloads.JwtAuthResponse;
import com.sunit.blog.payloads.UserDto;
import com.sunit.blog.security.JwtTokenHelper;
import com.sunit.blog.services.UserService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private final AuthenticationManager authenticationManager;
	@Autowired
    private final JwtTokenHelper jwtTokenHelper;
	@Autowired
    private final UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenHelper jwtTokenHelper, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenHelper = jwtTokenHelper;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		
    	 this.doAuthenticate(request.getUsername(),request.getPassword());
    	
    	 UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
    	 String token = this.jwtTokenHelper.generateToken(userDetails);
    	 
    	 JwtAuthResponse response = new JwtAuthResponse();
    	  response.setToken(token);
    	 return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
    	
    	
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
        	authenticationManager.authenticate(authentication);


        } catch (BadCredentialsException e) {
        	System.out.println("Invalid Details...!");
            throw new ApiException("Invalid Username or Password...!!");
        }

    }

	/*
	 * @ExceptionHandler(BadCredentialsException.class) public String
	 * exceptionHandler() { return "Credentials Invalid !!"; }
	 */
    
    // Register new api
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
    	
    	UserDto registeredUser = this.userService.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
    	
    }


}
