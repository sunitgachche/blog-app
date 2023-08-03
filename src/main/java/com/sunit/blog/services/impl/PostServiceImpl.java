package com.sunit.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunit.blog.entities.Category;
import com.sunit.blog.entities.Post;
import com.sunit.blog.entities.Users;
import com.sunit.blog.exceptions.ResourceNotFound;
import com.sunit.blog.payloads.PostDto;
import com.sunit.blog.payloads.PostResponse;
import com.sunit.blog.repositories.CategoryRepo;
import com.sunit.blog.repositories.PostRepo;
import com.sunit.blog.repositories.UserRepo;
import com.sunit.blog.services.PostService;

@Service
//@Transactional
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
	
		Users user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFound("User", "User id", userId));
		
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFound("Category", "Category id", categoryId));
		//Post post = new Post();
		
	//BeanUtils.copyProperties(postDto, post);
		Post post = modelMapper.map(postDto, Post.class);
	post.setImageName("default.png");
	post.setAddedDate(new Date());
	post.setUsers(user);
	post.setCategory(category);
	
	Post saveedPost = postRepo.save(post);
	
	
	//BeanUtils.copyProperties(saveedPost, postDto);
	PostDto postDto2 = modelMapper.map(saveedPost, PostDto.class);
	
		return postDto2;
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post postExist = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFound("Post", "Post id", postId));
		postExist.setContent(postDto.getContent());
		postExist.setImageName(postDto.getImageName());
		postExist.setTitle(postDto.getTitle());
		Post updatedSavedPost = postRepo.save(postExist);
		PostDto updatedPosts = modelMapper.map(updatedSavedPost, PostDto.class);
		
		return updatedPosts;
	}

	@Override
	public void deletePost(Integer postId) {

		 Post postExist = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFound("Post", "Post id", postId));
	   postRepo.delete(postExist);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber , Integer pageSize,String sortBy) {
		
		
		Pageable p=PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		Page<Post> pagePost = postRepo.findAll(p);
		List<Post> allPost = pagePost.getContent();
		List<PostDto> allPostDtos = allPost.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(allPostDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post postExist = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFound("Post", "Post id", postId));
		
		PostDto postDto = modelMapper.map(postExist, PostDto.class);
		return postDto;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		
		Category catExist = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFound("Category", "Category id", categoryId));
 
		List<Post> allPostofcat = postRepo.findByCategory(catExist);
		List<PostDto> allPost = allPostofcat.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return allPost;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		
		Users userExist = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFound("User", "User id", userId));
		
		List<Post> allPostByUsers = postRepo.findByUsers(userExist);
		 List<PostDto> allPost = allPostByUsers.stream().map((postRepo) -> this.modelMapper.map(postRepo, PostDto.class)).collect(Collectors.toList());
		return allPost;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts = postRepo.findByTitleContaining(keyword);
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	/*
	 * private Object modelMapper(Post post, Class<PostDto> class1) { // TODO
	 * Auto-generated method stub return null; }
	 */

}
