package com.sunit.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sunit.blog.entities.Category;
import com.sunit.blog.entities.Post;
import com.sunit.blog.entities.Users;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
	
	
	
   List<Post> findByUsers(Users users);
	
   List<Post> findByCategory(Category category);
   
   List<Post> findByTitleContaining(String title);
	
	

}
