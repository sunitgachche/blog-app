
package com.sunit.blog.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
	private Integer postId;

	@Column(name = "post_title", length = 100, nullable = false)
	private String title;

	@Column(name = "post_content", length = 1000, nullable = false)
	private String content;

	private String imageName;

	private Date addedDate;

	@ManyToOne(fetch = FetchType.EAGER)
	
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	
	@JoinColumn(name = "user_id")
	private Users users;
	
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Comment> comments= new ArrayList<>(); 

}
