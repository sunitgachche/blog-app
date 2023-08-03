package com.sunit.blog;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


import com.sunit.blog.config.AppConstants;
import com.sunit.blog.entities.Role;
import com.sunit.blog.repositories.RoleRepo;


@SpringBootApplication
//@ComponentScan("com.sunit.blog.repositories")

public class BlogAppApplication implements CommandLineRunner  {

	
	/* @Autowired private PasswordEncoder passwordEncoder; */
	
	
	  @Autowired 
	  private RoleRepo roleRepo;
	 

	
	 

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	

	
	
	  @Override public void run(String... args) throws Exception {
	  //System.out.println(this.passwordEncoder.encode("xyz"));
		
		  try {
		  
		  Role role = new Role(); role.setId(AppConstants.ADMIN_USER);
		  role.setName("ROLE_ADMIN");
		  
		  Role role1 = new Role(); role1.setId(AppConstants.NORMAL_USER);
		  role1.setName("ROLE_NORMAL");
		  
		  //to save into database 
		  List<Role> roles = List.of(role,role1);
		  List<Role> result = roleRepo.saveAll(roles);
		  
		  result.forEach(r-> {System.out.println(r.getName());
		  
		  });
		  
		  
		  
		  } catch (Exception e) { e.printStackTrace(); }
		  
		   
	  
	  }
	 
	  
	 

}
