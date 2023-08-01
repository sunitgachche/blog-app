package com.sunit.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunit.blog.entities.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer>{

}
