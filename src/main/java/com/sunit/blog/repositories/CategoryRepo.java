package com.sunit.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunit.blog.entities.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
