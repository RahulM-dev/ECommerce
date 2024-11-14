package com.anonymous.product.service;

import java.util.List;

import com.anonymous.product.dto.CategoryRequestDto;
import com.anonymous.product.dto.CategoryResponseDto;

public interface CategoryService {

	CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto);
	List<CategoryResponseDto> getAllCategories();
	boolean updateCategory(String categoryId, CategoryRequestDto categoryRequestDto);
	boolean deleteCategory(String categoryId);
}
