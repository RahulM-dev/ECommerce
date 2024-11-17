package com.anonymous.product.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.anonymous.product.dto.CartProductsDto;
import com.anonymous.product.dto.ProductRequestDto;
import com.anonymous.product.dto.ProductResponseDto;
import com.anonymous.product.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
		List<ProductResponseDto> products = this.productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Boolean> createProduct(@RequestBody ProductRequestDto product, MultipartFile imageFile) {
		boolean status = this.productService.createProduct(product, null);
		if (status) {
			return new ResponseEntity<>(status, HttpStatus.OK);
		}
		return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<Object> getProductById(@PathVariable String productId) {
		ProductResponseDto product = this.productService.getProductById(productId);
		if (product == null) {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<Object> getProductsByCategory(@PathVariable String categoryId) {
		List<ProductResponseDto> products = this.productService.getProductsByCategory(categoryId);
		if (products.isEmpty()) {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<Boolean> deleteProductById(@PathVariable String productId) {
		boolean status = this.productService.deleteProductById(productId);
		if (status) {
			return new ResponseEntity<>(status, HttpStatus.OK);
		}
		return new ResponseEntity<>(status, HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{productId}")
	public ResponseEntity<Boolean> updateProduct(@PathVariable String productId,
			@RequestBody ProductRequestDto product) {
		boolean status = this.productService.updateProduct(productId, product);
		if (status) {
			return new ResponseEntity<>(status, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
	}
	
//	@PatchMapping("/orderProduct/{productId}/{productQuantity}")
//	public ResponseEntity<Boolean> orderProduct(@PathVariable String productId, @PathVariable long productQuantity){
//		boolean status = this.productService.orderProduct(productId, productQuantity);
//		if(status) {
//			return new ResponseEntity<>(status, HttpStatus.ACCEPTED);
//		}
//		return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
//	}
	
	@PatchMapping("/orderProducts")
	public ResponseEntity<Boolean> orderProducts(@RequestBody List<CartProductsDto> cartProductsDtos){
		if(this.productService.orderProducts(cartProductsDtos)) {
			return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
	}

}
