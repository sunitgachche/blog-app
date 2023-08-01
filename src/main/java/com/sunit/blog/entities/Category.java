package com.sunit.blog.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name= "categories")
public class Category {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="category_id")
	private Integer categoryId;
	@Column(name = "title", length=100, nullable=false)
	private String categoryTitle;
	private String categoryDesc;
	
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL, orphanRemoval = true)
	
	private List<Post> posts = new ArrayList<>();
	
}
