package com.sunit.blog.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sunit.blog.entities.Category;
import com.sunit.blog.entities.Comment;
import com.sunit.blog.entities.Users;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PostDto {
	
	private Integer postId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private  Date addedDate;
	
	
	private CategoryDto category;
	
	private UserDto users;
	
	private List<CommentDto> comments= new ArrayList<>();


	

	
	
	
	
	

}
