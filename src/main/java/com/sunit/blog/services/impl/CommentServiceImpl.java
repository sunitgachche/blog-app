package com.sunit.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunit.blog.entities.Comment;
import com.sunit.blog.entities.Post;
import com.sunit.blog.exceptions.ResourceNotFound;
import com.sunit.blog.payloads.CommentDto;
import com.sunit.blog.repositories.CommentRepo;
import com.sunit.blog.repositories.PostRepo;
import com.sunit.blog.services.CommentService;


@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
	   Post postExist = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFound("Post", "Post id", postId));
		Comment comment = modelMapper.map(commentDto, Comment.class);
		comment.setPost(postExist);
		Comment saveedComment = commentRepo.save(comment);
		
		CommentDto commentDto2 = modelMapper.map(saveedComment, CommentDto.class);
		return commentDto2;
	}

	@Override
	public void deleteComment(Integer commentId) {
	
		Comment commentExist = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFound("Comment", "Comment id", commentId));
          commentRepo.delete(commentExist);
	}

}
