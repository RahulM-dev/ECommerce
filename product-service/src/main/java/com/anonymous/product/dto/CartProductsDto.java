package com.anonymous.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartProductsDto {
	
	private String productId;
	private long productStockQuantity;

}
