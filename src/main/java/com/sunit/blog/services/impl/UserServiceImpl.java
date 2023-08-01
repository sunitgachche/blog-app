package com.sunit.blog.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sunit.blog.config.AppConstants;
import com.sunit.blog.entities.Role;
import com.sunit.blog.entities.Users;
import com.sunit.blog.exceptions.ResourceNotFound;
import com.sunit.blog.payloads.UserDto;
import com.sunit.blog.repositories.RoleRepo;
import com.sunit.blog.repositories.UserRepo;
import com.sunit.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RoleRepo roleRepo;
	
	public UserServiceImpl(PasswordEncoder passwordEncoder) {
      
        this.passwordEncoder = passwordEncoder;
    }

	@Override
	public UserDto createUser(UserDto userDto) {
        
		
		  String encodedPwd = passwordEncoder.encode(userDto.getPwd());
		  userDto.setPwd(encodedPwd);
		 
		
		Users savedUser = userRepo.save(dtoToUser(userDto));

		return userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		Users users = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "id", userId));

		users.setAbout(userDto.getAbout());
		users.setEmail(userDto.getEmail());
		users.setName(userDto.getName());
		users.setPwd(userDto.getPwd());
		users.setUserId(userDto.getUserId());
		Users savedUsers = userRepo.save(users);
		return userToDto(savedUsers);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		Users userExist = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "id", userId));

		return userToDto(userExist);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<Users> users = userRepo.findAll();
		List<UserDto> userDto = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());

		return userDto;
	}

	@Override
	
	public void deleteUser(Integer userId) {

		Users users = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "id", userId));
		userRepo.delete(users);
	}

	public Users dtoToUser(UserDto userDto) {

		Users users = new Users();
		
		BeanUtils.copyProperties(userDto, users);

		/*
		 * Users users = new Users();
		 * users.setAbout(userDto.getAbout()); users.setEmail(userDto.getEmail());
		 * users.setName(userDto.getName()); users.setPwd(userDto.getPwd());
		 * users.setUserId(userDto.getUserId());
		 */

		return users;
	}

	public UserDto userToDto(Users user) {

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user, userDto);

		/*
		 * UserDto userDto = new UserDto(); userDto.setAbout(user.getAbout());
		 * userDto.setEmail(user.getEmail()); userDto.setName(user.getName());
		 * userDto.setPwd(user.getPwd()); userDto.setUserId(user.getUserId());
		 */
		return userDto;

	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
	Users user = this.modelMapper.map(userDto, Users.class);
	user.setPwd( this.passwordEncoder.encode(userDto.getPwd()));
	
	// if new user created by default having normal role
	Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
	user.getRoles().add(role);
	Users newUser = this.userRepo.save(user);
		return this.modelMapper.map(newUser, UserDto.class);
	}

}
