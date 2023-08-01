package com.sunit.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunit.blog.payloads.ApiResponse;
import com.sunit.blog.payloads.UserDto;
import com.sunit.blog.services.UserService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		
		UserDto createdUser = userService.createUser(userDto);
		
		return new ResponseEntity<UserDto>(createdUser, HttpStatus.CREATED);
		
	}
	@PutMapping("/update/{userid}")
	public ResponseEntity<UserDto> updateUser(@Valid @PathVariable Integer userid ,@RequestBody UserDto userDto){
		
		UserDto updatedUser = userService.updateUser(userDto, userid);
		return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete/{userid}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userid") Integer uid){
		userService.deleteUser(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user delted successfully..!",true), HttpStatus.OK);
		
	}
	@GetMapping("/get/{userid}")
	public ResponseEntity<UserDto> getSingleuser(@PathVariable Integer userid){
		
		UserDto userById = userService.getUserById(userid);
		return new ResponseEntity<UserDto>(userById, HttpStatus.OK);
		
	}
	@GetMapping("/get")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> allUsers = userService.getAllUsers();
		return new ResponseEntity<List<UserDto>>(allUsers, HttpStatus.OK);
		
	}

}
