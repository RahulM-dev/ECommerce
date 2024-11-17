package com.anonymous.product.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.anonymous.product.dto.CategoryRequestDto;
import com.anonymous.product.dto.CategoryResponseDto;
import com.anonymous.product.model.Category;
import com.anonymous.product.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductService productService;

	@Override
	public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
		Category category = convertRequestDtoToCategory(categoryRequestDto);
		category.setCategoryId(UUID.randomUUID().toString());
		category.setUpdatedAt(LocalDateTime.now());
		categoryRepository.save(category);
		return convertToResponseDto(category);
	}

	@Override
	public List<CategoryResponseDto> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		return categories.parallelStream()
				.map(this::convertToResponseDto)
				.collect(Collectors.toList());
	}

	@Override
	public boolean updateCategory(String categoryId, CategoryRequestDto categoryRequestDto) {
		Category category = categoryRepository.findById(categoryId)
								.orElseThrow(() -> new RuntimeException("Category not found"));
		category.setCategoryName(categoryRequestDto.getCategoryName());
		category.setCategoryDescription(categoryRequestDto.getCategoryDescription());
		categoryRepository.save(category);
		return true;
	}

	@Override
	public boolean deleteCategory(String categoryId) {
		categoryRepository.deleteById(categoryId);
		productService.deleteProductsByCategoryId(categoryId);
		return true;
	}
	
	public Category convertRequestDtoToCategory(CategoryRequestDto categoryRequestDto) {
		return this.modelMapper.map(categoryRequestDto, Category.class);
	}
	
	public CategoryRequestDto convertToRequestDto(Category category) {
		return this.modelMapper.map(category, CategoryRequestDto.class);
	}
	
	public Category convertResponseDtoToCategory(CategoryResponseDto categoryResponseDto) {
		return this.modelMapper.map(categoryResponseDto, Category.class);
	}
	
	public CategoryResponseDto convertToResponseDto(Category category) {
		return this.modelMapper.map(category, CategoryResponseDto.class);
	}

}
