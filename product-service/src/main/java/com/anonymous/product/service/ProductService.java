package com.anonymous.product.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.anonymous.product.dto.ProductRequestDto;
import com.anonymous.product.dto.ProductResponseDto;

public interface ProductService {
	
	boolean createProduct(ProductRequestDto productDto, MultipartFile imageFile);
	ProductResponseDto getProductById(String productId);
	List<ProductResponseDto> getAllProducts();
	List<ProductRequestDto> getProductsByCategory();
	boolean deleteProductById(String productId);
	ProductRequestDto updateProduct(String productId, ProductRequestDto productDto);

}