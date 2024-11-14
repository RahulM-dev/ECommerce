package com.anonymous.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

	private String productName;
	private String productCategoryId;
	private double productPrice;
	private long productStockQuantity;

}
