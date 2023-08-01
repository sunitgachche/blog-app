package com.sunit.blog.services;

import java.util.List;

import com.sunit.blog.entities.Post;
import com.sunit.blog.payloads.PostDto;
import com.sunit.blog.payloads.PostResponse;

public interface PostService {
	
	  PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	    PostDto updatePost(PostDto postDto, Integer postId);
	  void  deletePost(Integer postId);
	  PostResponse getAllPosts(Integer pageNumber , Integer pageSize,String sortBy);
	  PostDto getPostById(Integer postId);
	  List<PostDto> getPostsByCategory(Integer categoryId);
	  List<PostDto> getPostsByUser(Integer userId);
	  List<PostDto> searchPost(String keyword);

}
