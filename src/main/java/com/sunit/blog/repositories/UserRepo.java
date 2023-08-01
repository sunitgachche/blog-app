package com.sunit.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunit.blog.entities.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {

	
	Optional<Users> findByEmail(String email);
}

