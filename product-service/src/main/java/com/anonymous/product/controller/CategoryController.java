package com.anonymous.product.controller;

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
import com.anonymous.product.dto.CategoryRequestDto;
import com.anonymous.product.dto.CategoryResponseDto;
import com.anonymous.product.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<CategoryResponseDto>> getAllCategories() {
		List<CategoryResponseDto> categories = this.categoryService.getAllCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto category) {
		CategoryResponseDto categoryResponseDto = this.categoryService.createCategory(category);
		return new ResponseEntity<>(categoryResponseDto, HttpStatus.OK);
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<Boolean> updateCategory(@PathVariable String categoryId,
			@RequestBody CategoryRequestDto category) {
		boolean status = this.categoryService.updateCategory(categoryId, category);
		if (status) {
			return new ResponseEntity<>(status, HttpStatus.OK);
		}
		return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<Boolean> deleteCategory(@PathVariable String categoryId) {
		boolean status = this.categoryService.deleteCategory(categoryId);
		if (status) {
			return new ResponseEntity<>(status, HttpStatus.OK);
		}
		return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
	}

}
