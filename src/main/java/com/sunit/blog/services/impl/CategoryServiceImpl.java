package com.sunit.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunit.blog.entities.Category;
import com.sunit.blog.exceptions.ResourceNotFound;
import com.sunit.blog.payloads.CategoryDto;
import com.sunit.blog.repositories.CategoryRepo;
import com.sunit.blog.services.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {
	 
	@Autowired
	 public CategoryRepo categoryRepo;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		
		Category category = new Category();
	    BeanUtils.copyProperties(categoryDto, category);
	    Category savedCat = categoryRepo.save(category);
	    
	    BeanUtils.copyProperties(savedCat, categoryDto);
	     
		return categoryDto;
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer catId) {
		
		Category catExist = categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFound("category", "cat id", catId));
		
		catExist.setCategoryDesc(categoryDto.getCategoryDesc());
		
		catExist.setCategoryTitle(categoryDto.getCategoryTitle());
		
		Category savedCat = categoryRepo.save(catExist);
		BeanUtils.copyProperties(savedCat, categoryDto);
		return categoryDto;
	}

	@Override
	public void deleteCategory(Integer catId) {
		
		Category catExist = categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFound("category", "cat id", catId));
	
		categoryRepo.delete(catExist);
	}

	@Override
	public CategoryDto getCategory(Integer catId) {
		
		Category catExist = categoryRepo.findById(catId).orElseThrow(() -> new ResourceNotFound("category", "cat id", catId));
		CategoryDto categoryDto = new CategoryDto();
 		BeanUtils.copyProperties(catExist,categoryDto );
		
		return categoryDto;
	}

	@Override
	public List<CategoryDto> getAllCat() {
		
		List<Category> allCat = categoryRepo.findAll();
		List<CategoryDto> allCategoryDtos = allCat.stream().map(cat -> {
			CategoryDto categoryDto = new CategoryDto();
			categoryDto.setCategoryId(cat.getCategoryId());
			categoryDto.setCategoryDesc(cat.getCategoryDesc());
			categoryDto.setCategoryTitle(cat.getCategoryTitle());
			
			return categoryDto;
		})
		.collect(Collectors.toList());
		return allCategoryDtos;
		
	}

}
