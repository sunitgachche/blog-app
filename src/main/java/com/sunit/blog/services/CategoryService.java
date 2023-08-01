package com.sunit.blog.services;

import java.util.List;

import com.sunit.blog.payloads.CategoryDto;

public interface CategoryService {
	
	
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer catId);
	
	  public void deleteCategory(Integer catId);
	  
	  public CategoryDto getCategory(Integer catId);
	    public List<CategoryDto> getAllCat();
	   
	
	

}
