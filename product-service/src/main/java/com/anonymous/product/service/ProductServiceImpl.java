package com.anonymous.product.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.anonymous.product.dto.CartProductsDto;
import com.anonymous.product.dto.ProductRequestDto;
import com.anonymous.product.dto.ProductResponseDto;
import com.anonymous.product.model.Product;
import com.anonymous.product.repository.CategoryRepository;
import com.anonymous.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public boolean createProduct(ProductRequestDto productDto, MultipartFile imageFile) {
		try {
			Product product = convertRequestDtoToProduct(productDto);
			String productId = UUID.randomUUID().toString();
			product.setProductId(productId);
			String productImageURL = imageUploadToGumlet(imageFile, productId);
			product.setProductImageURL(productImageURL);
			product.setProductCreatedAt(LocalDateTime.now());
			productRepository.save(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public ProductResponseDto getProductById(String productId) {
		Optional<Product> product = productRepository.findById(productId);
		if (product.isPresent()) {
			return convertToResponseDto(product.get());
		}
		// I will write custom exception
		throw new RuntimeException("Product isn't found");
	}

	@Override
	public List<ProductResponseDto> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products.parallelStream().map(this::convertToResponseDto).collect(Collectors.toList());
	}

	@Override
	public List<ProductResponseDto> getProductsByCategory(String categoryId) {
		List<Product> products = productRepository.findByCategoryId(categoryId);
		return products.parallelStream().map(this::convertToResponseDto).collect(Collectors.toList());
	}

	@Override
	public boolean deleteProductById(String productId) {
		productRepository.deleteById(productId);
		return true;
	}

	@Override
	public boolean updateProduct(String productId, ProductRequestDto productDto) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		product.setProductName(productDto.getProductName());
		product.setProductPrice(productDto.getProductPrice());
		product.setProductStockQuantity(productDto.getProductStockQuantity());
		this.productRepository.save(product);
		return true;
	}

	@Override
	public boolean deleteProductsByCategoryId(String categoryId) {
		categoryRepository.deleteByCategoryId(categoryId);
		return true;
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

	public String imageUploadToGumlet(MultipartFile file, String productId) {
		// Write the logic to upload the files to Gumlet Service

		return "Return the unique name";
	}

//	@Override
//	public boolean orderProduct(String productId, long productQuantity) {
//		Product product = this.productRepository.findById(productId).get();
//		if (product == null || product.getProductStockQuantity() < productQuantity) {
//			return false;
//		}
//		product.setProductStockQuantity(product.getProductStockQuantity() - productQuantity);
//		this.productRepository.save(product);
//		return true;
//	}
	
	@Override
	@Transactional
	public boolean orderProducts(List<CartProductsDto> productsDtos) {
		List<Product> products = productsDtos.stream().map(
										item -> this.productRepository.findById(
												item.getProductId()).orElse(null))
										.filter(Objects::nonNull)
										.toList();
		if(products.size() != productsDtos.size()) {
			return false;
		}
		for(int i = 0; i < products.size(); i++) {
			Product product = products.get(i);	
			CartProductsDto cartProductDto = productsDtos.get(i);
			
			if(product.getProductStockQuantity() < cartProductDto.getProductStockQuantity()) {
				return false;
			}
			product.setProductStockQuantity(product.getProductStockQuantity() - cartProductDto.getProductStockQuantity());
			this.productRepository.save(product);
		}
		return true;
	}

}
