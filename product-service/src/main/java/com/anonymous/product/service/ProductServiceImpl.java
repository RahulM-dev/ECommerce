package com.anonymous.product.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.anonymous.product.dto.ProductRequestDto;
import com.anonymous.product.dto.ProductResponseDto;
import com.anonymous.product.model.Product;
import com.anonymous.product.repository.ProductRepository;

public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public boolean createProduct(ProductRequestDto productDto, MultipartFile imageFile) {
		try {
			Product product = convertRequestDtoToProduct(productDto);
			String productImageURL = imageUploadToGumlet(imageFile);
			product.setProductImageURL(productImageURL);
			productRepository.save(product);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public ProductResponseDto getProductById(String productId) {
		Optional<Product> product = productRepository.findById(productId);
		if(product.isPresent()) {
			return convertToResponseDto(product.get());
		}
		//Write custom exception
		throw new RuntimeException("Product isn't found");
	}

	@Override
	public List<ProductResponseDto> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products.parallelStream()
				.map(this::convertToResponseDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductRequestDto> getProductsByCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteProductById(String productId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ProductRequestDto updateProduct(String productId, ProductRequestDto productDto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Product convertRequestDtoToProduct(ProductRequestDto productRequestDto) {
		return this.modelMapper.map(productRequestDto, Product.class);
	}
	
	public ProductRequestDto convertToRequestDto(Product product) {
		return this.modelMapper.map(product, ProductRequestDto.class);
	}
	
	public Product convertResponseDtoToProduct(ProductResponseDto productResponseDto) {
		return this.modelMapper.map(productResponseDto, Product.class);
	}
	
	public ProductResponseDto convertToResponseDto(Product product) {
		return this.modelMapper.map(product, ProductResponseDto.class);
	}
	
	public String imageUploadToGumlet(MultipartFile file) {
		//Write the logic to upload the files to Gumlet Service
		
		return "Return the unique name";
	}

}
