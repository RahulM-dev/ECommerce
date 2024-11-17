package com.anonymous.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

	private String orderItemId;
	private String productName;
	private double productPrice;
	private int productQuantity;
	private String productImageURL;

}
