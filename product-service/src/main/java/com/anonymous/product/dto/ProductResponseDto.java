package com.anonymous.product.dto;

import com.anonymous.product.model.Category;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
	
	private String productId;
	private String productName;

	@ManyToOne
	private Category productCategory;

	private double productPrice;
	private String productImageUrl;
	private long productStockQuantity;


}
