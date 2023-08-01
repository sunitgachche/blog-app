package com.sunit.blog.payloads;




import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDto {
	
	private Integer categoryId;
	
	@NotBlank
	@Size(min=4, message = "minimum size of categoryTitle is 4")
	private String categoryTitle;
	@NotBlank
	@Size(min=10, message = "minimum size of description is 10 ")
	private String categoryDesc;

}
