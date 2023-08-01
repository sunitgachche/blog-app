
package com.sunit.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunit.blog.payloads.ApiResponse;
import com.sunit.blog.payloads.CategoryDto;
import com.sunit.blog.services.CategoryService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/cat")
public class categoryController {
	@Autowired
	private CategoryService categoryService;

	@PostMapping("/create")
	public ResponseEntity<CategoryDto> createCat(@Valid @RequestBody CategoryDto categoryDto) {

		CategoryDto createdCategory = categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createdCategory, HttpStatus.CREATED);

	}

	@PutMapping("/create/{id}")
	public ResponseEntity<CategoryDto> updateCat(@Valid @PathVariable Integer id, @RequestBody CategoryDto categoryDto) {

		CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, id);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteCat(@PathVariable Integer id) {

		categoryService.deleteCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully...!", true),
				HttpStatus.OK);

	}
	@GetMapping("/get/{id}")
	public ResponseEntity<CategoryDto> getcat(@PathVariable Integer id){
		CategoryDto category = categoryService.getCategory(id);
		return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);
		
	}
	@GetMapping("/get")
	public ResponseEntity<List<CategoryDto>> getall(){
		List<CategoryDto> allCat = categoryService.getAllCat();
		return new ResponseEntity<List<CategoryDto>>(allCat, HttpStatus.OK);
		
	}
}
